package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class listen extends function {
    public static final String name="Listen";
    private int silenceLimit;
    HashMap<String,String> toRecongnize=new HashMap<>();

    @Override
    public String exe(globalInfo globalInfo) {


        String toGo= globalInfo.getIn().gets(silenceLimit,toRecongnize.keySet()).getInfo();
        globalSetting.log.info("listen:"+toGo);
        if(toGo==registry.listenFailure)
            return registry.listenFailure;
        else if(toRecongnize.containsKey(toGo))
            return toRecongnize.get(toGo);
        else if(!toRecongnize.containsKey(toGo)&& toRecongnize.containsKey(registry.listenDefault))
            return toRecongnize.get(registry.listenDefault);
        else
            return registry.nlpFailure;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        listen func=new listen();
        func.silenceLimit=jsonObject.getInt(registry.silenceLimt);
        for(String str: jsonObject.getJSONObject(registry.goTo).keySet()){
            func.toRecongnize.put(str,jsonObject.getJSONObject(registry.goTo).getString(str));
        }
        return func;
    }
}
