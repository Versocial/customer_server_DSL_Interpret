package vlang.interpreter.compilers;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.compiler;

import java.io.File;
import java.util.Scanner;

public class jsonCompiler implements compiler {
    boolean readed=false;
    String word;
    JSONObject jsonObject=new JSONObject();
    Scanner scanner ;

    @Override
    public void compile(String path) {
        if(!new File(path).isFile())
            globalSetting.log.warning("Can not open file: "+path);
        else
            globalSetting.log.info("Open file Successfully:"+path);

        //while()

    }


}
