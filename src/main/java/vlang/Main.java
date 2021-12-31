package vlang;

import vlang.interpreter.Executor;
import vlang.interpreter.exefactorys.ExeFactoryByJson;
import vlang.interpreter.GlobalInfo;

import java.util.Objects;

/**
 * 主函数
 */
public class Main {
    /**
     * 主函数
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            test();
            return;
        }
        switch (args[0]) {
            case "parse":
                parse(args[1], args[2]);
                break;
            case "exe":
                exe(args[1]);
                break;
            default:
                System.out.println("Command Wrong: \n 1. parse srcPath outPath\n 2. exe path \n");
        }
        return;
    }

    static private void test() {
        GlobalSetting.init();
        //语法分析，并生成json格式的中间脚本
        boolean ok = GlobalSetting.parser.parse(Objects.requireNonNull(Main.class.getClassLoader().getResource("./test.txt")).getPath(), "src\\main\\resources\\out.json");
        if (!ok)
            return;
        //执行
        Executor e = new ExeFactoryByJson().createBy("src\\main\\resources\\out.json");
        Thread exe = new Thread(e.runner(new GlobalInfo(123456)));
        exe.start();
        try {
            exe.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        GlobalSetting.log.info("finish a dialog.");

    }

    /**
     * 分析并导出中间脚本
     *
     * @param pathIn  源文件路径
     * @param pathOut 输出中间脚本路径
     */
    private static void parse(String pathIn, String pathOut) {
        GlobalSetting.init();
        //语法分析，并生成json格式的中间脚本
        boolean ok = GlobalSetting.parser.parse(Objects.requireNonNull(pathIn), pathOut);
        if (!ok)
            System.out.println("Fail");
    }

    /**
     * 执行
     *
     * @param path 中间脚本路径
     */
    private static void exe(String path) {
        GlobalSetting.init();
        //执行
        Executor e = new ExeFactoryByJson().createBy(path);
        Thread exe = new Thread(e.runner(new GlobalInfo(123456)));
        exe.start();
        try {
            exe.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        GlobalSetting.log.info("finish a dialog.");
    }
}