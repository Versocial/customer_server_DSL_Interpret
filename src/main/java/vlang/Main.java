package vlang;

import vlang.interpreter.executor;
import vlang.interpreter.exefactorys.exeFactoryByJson;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        globalSetting.init();
        executor e= new exeFactoryByJson().createBy(Main.class.getClassLoader().getResource("./test.json").getPath());
        e.run();
    }
}