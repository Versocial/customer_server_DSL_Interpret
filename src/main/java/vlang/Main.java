package vlang;

import vlang.interpreter.executor;
import vlang.interpreter.exefactorys.exeFactoryByJson;

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
        globalSetting.parser.parse(Main.class.getClassLoader().getResource("./test.txt").getPath(),"src\\main\\resources\\out.json");
        executor e= new exeFactoryByJson().createBy("src\\main\\resources\\out.json");
        e.run();
    }
}