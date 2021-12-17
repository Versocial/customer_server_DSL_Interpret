package vlang.interpreter.parsers;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.Step;
import vlang.interpreter.parser;
import vlang.interpreter.parsers.rowJsonParser.rowJsonParser;
import vlang.interpreter.registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class jsonParser implements parser {
    boolean readed=false;
    String word;
    JSONObject jsonObject=new JSONObject();
    Scanner scanner ;

    @Override
    public void parse(String inPath,String outPath) {

        JSONObject jsonObject=null;

        if(!new File(inPath).isFile()) {
            globalSetting.log.warning("Can not open file: " + inPath);
            return;
        }
        else
            globalSetting.log.info("Open file Successfully:"+inPath);

        try {
            jsonObject = new rowJsonParser().parse(inPath);
        } catch ( IOException e) {
            e.printStackTrace();
        }
        if(jsonObject==null){
            return;
        }

        check(jsonObject);

        BufferedWriter writer= null;
        try {
            writer = new BufferedWriter( new FileWriter( outPath));
            writer.write( jsonObject.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            globalSetting.log.warning("Can not write file: " + outPath);
        }

    }


    boolean check(JSONObject jsonObject){
        int errorNum=0;
        for(String key :jsonObject.keySet()){
            if(key.equals(registry.entry))
                continue;
            else {
                JSONArray jsonArray=jsonObject.getJSONArray(key);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject json = (JSONObject)jsonArray.get(i);
                    if(registry.func.get(json.getString(registry.function)).hasError(json,jsonObject)){
                        globalSetting.log.warning("Error when check function "+json.getString(registry.function)+", which is the NO."+(i+1)+" function for Step "+key);
                        errorNum++;
                    }
                }
            }
        }
        return errorNum>0;
    }


}
