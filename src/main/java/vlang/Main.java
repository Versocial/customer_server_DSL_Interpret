package vlang;

import vlang.interpreter.factorys.jsonFactory;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        globalSetting.init();
        new jsonFactory().createBy(Main.class.getClassLoader().getResource("./test.json").getPath());
    }
}