package vlang.interpreter.functions;


import org.json.JSONArray;
import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.parsers.word;
import vlang.interpreter.registry;

import java.util.ArrayList;
import java.util.HashMap;

public class listen extends function {
    public static final String name="Listen";
    public static final String silence=registry.listenSilence;
    public static final String branch="Branch";
    public static final String defaults=registry.listenDefault;
    private int silenceLimit;
    private Integer index=0;

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

    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(registry.goTo,new JSONArray());
        int t= Math.max(input.size(), 2);
        word w = new word("@unkown");
        boolean repeat=true;
        for( index = 0;index<t&&repeat;index++){
            if(index<input.size())
                w= new word(input.get(index));
            switch (index){
                case 0:
                    if(index>=input.size()){
                        globalSetting.log.warning("expected num but got nothing");
                        repeat=false;
                        break;
                    }
                    if(w.getType()!=word.Type.num){
                        globalSetting.log.warning("expected num but got "+w.getInfo());
                        repeat=false;
                        break;
                    }
                    jsonObject.put(registry.silenceLimt,Integer.parseInt(w.getInfo()));
                    break;
                default:
                    if(w.getType()!=word.Type.branch){
                        globalSetting.log.warning("expected 'branch' but got "+w.getInfo());
                        repeat=false;
                        break;
                    }
                    repeat=gotBranch(jsonObject,input);
                    break;
            }

        }
        if(repeat==false)
            globalSetting.log.warning("Parser failed on Listen function.");
        return jsonObject;
    }


    private boolean gotBranch(JSONObject jsonObject,ArrayList<String>input){
        boolean ok=false;
        word w=new word(input.get(index));
        switch (w.getInfo()){
            case silence :case defaults:
                ok=manageSilenceDefault(jsonObject,input);
                break;
            case branch:
                ok=manageBranch(jsonObject, input);
                break;
            default:
                globalSetting.log.warning("expected 'branch' but got "+w.getInfo());
                ok=false;
                break;
        }
        return ok;
    }

    private boolean manageSilenceDefault(JSONObject jsonObject,ArrayList<String>input){
        boolean ok=false;
        if(jsonObject.has(input.get(index))) {
            globalSetting.log.warning("The Listen Already has a Step id " + jsonObject.get(input.get(index)) + " for " + input.get(index));
            return ok;
        }
        index++;
        if(index>=input.size())
            globalSetting.log.warning("expected a Step Id but got nothing");
        else if(new word(input.get(index)).getType()!=word.Type.id)
            globalSetting.log.warning("expected a Step Id but got "+input.get(index));
        else {
            jsonObject.getJSONArray(registry.goTo).put(new JSONObject().put(new word(input.get(index-1)).getInfo(),input.get(index)));
            ok=true;
        }
        return ok;
    }

    private boolean manageBranch(JSONObject jsonObject,ArrayList<String>input){
        boolean ok=false;
        index++;
        if(index>=input.size())
            globalSetting.log.warning("expected a String but got nothing");
        else if(new word(input.get(index)).getType()!=word.Type.string)
            globalSetting.log.warning("expected a String but got "+input.get(index));
        else if(jsonObject.has(input.get(index)))
            globalSetting.log.warning("The Listen Already has a Step id " + jsonObject.get(input.get(index)) + " for " + input.get(index));
        else
            ok=manageSilenceDefault(jsonObject, input);
        return ok;
    }
}
