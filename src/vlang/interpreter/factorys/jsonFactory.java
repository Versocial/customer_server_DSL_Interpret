package vlang.interpreter.factorys;

import netscape.javascript.JSObject;
import vlang.globalSetting;
import vlang.interpreter.globalInfo;
import vlang.interpreter.interpreter;
import vlang.interpreter.interpreterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class jsonFactory implements interpreterFactory {

    @Override
    public interpreter createBy(String path) {
        Scanner scanner=null;
        try {
            scanner=new Scanner(Paths.get(path), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        globalSetting.log.info("starting interpreter by "+path+"..");
        interpreter newInterpreter=new interpreter(new globalInfo());

        String str="";
        while (scanner.hasNextLine())
            str+=scanner.nextLine();


        JSONObject

        return newInterpreter;
    }
}
