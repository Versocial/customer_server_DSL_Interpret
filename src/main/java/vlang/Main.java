package vlang;

import vlang.interpreter.executor;
import vlang.interpreter.exefactorys.exeFactoryByJson;
import vlang.interpreter.parsers.jsonParser.jsonParser;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        globalSetting.init();
        globalSetting.parser.parse(Main.class.getClassLoader().getResource("./test.txt").getPath(),"src\\main\\resources\\out.json");
        executor e= new exeFactoryByJson().createBy("src\\main\\resources\\out.json");
        e.run();
    }
}