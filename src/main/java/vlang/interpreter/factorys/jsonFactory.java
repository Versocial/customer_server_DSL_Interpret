package vlang.interpreter.factorys;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class jsonFactory implements interpreterFactory {

    @Override
    public executor createBy(String path) {
        Scanner scanner=null;
        if(new File(path).isFile())
            System.out.println("is");
        else
            System.out.println("not");
        try {
            scanner=new Scanner(new File(path), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        globalSetting.log.info("starting interpreter by "+path+"..");
        globalInfo info=new globalInfo();
        executor newExecutor=new executor(info);

        String str="";
        while (scanner.hasNextLine())
            str+=scanner.nextLine();

        JSONObject jsonObject=new JSONObject(str);
        for(String key :jsonObject.keySet()){
            if(key.equals(registry.entry))
                newExecutor.setEntryStep(jsonObject.getString(key));
            else {
                Step step=new Step(key,info);
                JSONArray jsonArray=jsonObject.getJSONArray(key);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject json = (JSONObject)jsonArray.get(i);
                    step.addFunction(registry.func.get(json.getString(registry.function)).buildByJson(json));
                }
            }
        }

        return newExecutor;
    }
}
