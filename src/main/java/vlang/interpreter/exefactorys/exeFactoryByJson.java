package vlang.interpreter.exefactorys;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 该类的功能是：读取待执行脚本，生成对应的执行器。
 */
public class exeFactoryByJson implements executorFactory {
    /**
     *
     * @param path {@link parser}分析后输出的中间脚本的路径。
     * @return 生成的执行器
     */
    @Override
    public executor createBy(String path) {
        Scanner scanner=null;
        if(!new File(path).isFile())
            globalSetting.log.warning("Can not open file: "+path);
        else
            globalSetting.log.info("Open file Successfully:"+path);

        try {
            scanner=new Scanner(new File(path), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        globalSetting.log.info("starting interpreter by "+path+"..");
        executor newExecutor=new executor();

        String str="";
        while (scanner.hasNextLine())
            str+=scanner.nextLine();

        JSONObject jsonObject=new JSONObject(str);
        for(String key :jsonObject.keySet()){
            if(key.equals(registry.entry))
                newExecutor.setEntryStep(jsonObject.getString(key));
            else {
                step step=new step(key);
                JSONArray jsonArray=jsonObject.getJSONArray(key);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject json = (JSONObject)jsonArray.get(i);
                    step.addFunction(registry.func.get(json.getString(registry.function)).buildByJson(json));
                }
                newExecutor.addStep(step);
            }
        }

        return newExecutor;
    }
}
