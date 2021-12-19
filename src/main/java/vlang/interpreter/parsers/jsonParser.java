package vlang.interpreter.parsers;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.parser;
import vlang.interpreter.parsers.rowJsonParser.rowJsonParser;
import vlang.interpreter.registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 把JSONObject作为输出（执行器执行的中间脚本为json文件）的语法分析器。
 */
public class jsonParser implements parser {

    /**
     * 语法分析方法，调用{@link rowJsonParser}的parse方法进行分析然后用checkError方法进行检查。
     * @param inPath 输入的源文件路径
     * @param outPath 输出的可被执行器执行的脚本路径
     */
    @Override
    public boolean parse(String inPath,String outPath) {

        JSONObject jsonObject=null;
        //打开源文件
        if(!new File(inPath).isFile()) {
            globalSetting.log.warning("Can not open file: " + inPath);
            return false;
        }
        else
            globalSetting.log.info("Open file Successfully:"+inPath);
        //进行语法分析
        try {
            jsonObject = new rowJsonParser().parse(inPath);
        } catch ( IOException e) {
            e.printStackTrace();
        }
        if(jsonObject==null){
            return false;
        }
        //补充式检错
        if(checkError(jsonObject))
            return false;
        //将结果写入输出文件
        BufferedWriter writer= null;
        try {
            writer = new BufferedWriter( new FileWriter( outPath));
            writer.write( jsonObject.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            globalSetting.log.warning("Can not write file: " + outPath);
            return false;
        }
        return true;

    }

    /**
     * 检查JSONObject是否含有错误
     * @param jsonObject 语法分析产生的待转化为执行器的JSONObject实例。
     * @return 若检测到错误返回true，否则返回false。
     */
    boolean checkError(JSONObject jsonObject){
        int errorNum=0;
        for(String key :jsonObject.keySet()){
            if(key.equals(registry.entry))
                continue;
            else {
                JSONArray jsonArray=jsonObject.getJSONArray(key);
                //调用各函数的检错方法进行检错
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject json = (JSONObject)jsonArray.get(i);
                    if(registry.func.get(json.getString(registry.function)).hasErrorByJson(json,jsonObject)){
                        globalSetting.log.warning("Error when check function "+json.getString(registry.function)+", which is the NO."+(i+1)+" function for Step "+key);
                        errorNum++;
                    }
                }
            }
        }
        if(errorNum>0)
            globalSetting.log.warning("Parse Fail while check : "+errorNum+" errors detected.");
        return errorNum>0;
    }


}
