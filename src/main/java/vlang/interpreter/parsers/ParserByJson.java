package vlang.interpreter.parsers;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.parser;

import java.io.File;
import java.util.Scanner;

public class ParserByJson implements parser {
    boolean readed=false;
    String word;
    JSONObject jsonObject=new JSONObject();
    Scanner scanner ;

    @Override
    public void parse(String inPath,String outPath) {
        if(!new File(inPath).isFile())
            globalSetting.log.warning("Can not open file: "+inPath);
        else
            globalSetting.log.info("Open file Successfully:"+inPath);

        //while()

    }


}
