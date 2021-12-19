package vlang;

import vlang.interpreter.executor;
import vlang.interpreter.exefactorys.exeFactoryByJson;
import vlang.interpreter.globalInfo;

import java.io.*;

/**
 * 主函数
 */
public class Main {
    /**
     * 主函数
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        globalSetting.init();
        boolean ok= globalSetting.parser.parse(Main.class.getClassLoader().getResource("./test.txt").getPath(),"src\\main\\resources\\out.json");
        if(!ok)
            return;
        executor e= new exeFactoryByJson().createBy("src\\main\\resources\\out.json");
        Thread exe= new Thread(e.runner(new globalInfo()));
        exe.start();
        try {
            exe.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        globalSetting.log.info("finish a dialog.");
    }
}