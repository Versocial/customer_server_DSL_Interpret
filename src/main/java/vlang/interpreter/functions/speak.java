package vlang.interpreter.functions;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import java.util.ArrayList;

public class speak extends function {
    public static final String name="Speak";
    private  ArrayList<String> toSpeak=new ArrayList<>();

    @Override
    public String exe(globalInfo globalInfo) {
        globalSetting.log.info("speak");
        for( String str : toSpeak) {
            globalInfo.getOut().puts(str);
        }
        return registry.goOn;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        speak func=new speak();
        for(int i=0;i<jsonObject.getJSONArray(registry.param).length();i++){
            func.toSpeak.add(jsonObject.getJSONArray(registry.param).getString(i));
        }
        return func;
    }

    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<input.size();i++)
            jsonArray.put(input.get(i));
        jsonObject.put(registry.param,jsonArray);
        return jsonObject;
    }
}
