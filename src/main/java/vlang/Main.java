package vlang;

import vlang.interpreter.executor;
import vlang.interpreter.exefactorys.exeFactoryByJson;
import vlang.interpreter.globalInfo;

import java.util.Objects;

/**
 * 主函数
 */
public class Main {
    /**
     * 主函数
     */
    public static void main(String[] args){
        globalSetting.init();
        //语法分析，并生成json格式的中间脚本
        boolean ok= globalSetting.parser.parse(Objects.requireNonNull(Main.class.getClassLoader().getResource("./test.txt")).getPath(),"src\\main\\resources\\out.json");
        if(!ok)
            return;
        //执行
        executor e= new exeFactoryByJson().createBy("src\\main\\resources\\out.json");
        Thread exe= new Thread(e.runner(new globalInfo(123456)));
        exe.start();
        try {
            exe.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        globalSetting.log.info("finish a dialog.");
    }
}