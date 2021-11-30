package vlang.interpreter.factorys;

import vlang.globalSetting;
import vlang.interpreter.globalInfo;
import vlang.interpreter.interpreter;
import vlang.interpreter.interpreterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class jsonFactory implements interpreterFactory {

    @Override
    public interpreter createBy(String path) {
        try {
            Scanner scanner=new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.out.println("[]FileNotFound:");
            e.printStackTrace();
        }
        globalSetting.log.info("starting interpreter by "+path+"..");
        interpreter newInterpreter=new interpreter(new globalInfo());



        return newInterpreter;
    }
}
