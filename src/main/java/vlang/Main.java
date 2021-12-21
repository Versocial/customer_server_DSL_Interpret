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
    public static void main(String[] args){
        GlobalSetting.init();
        //语法分析，并生成json格式的中间脚本
        boolean ok= GlobalSetting.parser.parse(Objects.requireNonNull(Main.class.getClassLoader().getResource("./test.txt")).getPath(),"src\\main\\resources\\out.json");
        if(!ok)
            return;
        //执行
        Executor e= new ExeFactoryByJson().createBy("src\\main\\resources\\out.json");
        Thread exe= new Thread(e.runner(new GlobalInfo(123456)));
        exe.start();
        try {
            exe.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        GlobalSetting.log.info("finish a dialog.");

    }
}