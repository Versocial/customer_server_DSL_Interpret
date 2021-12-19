package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.parsers.word;
import vlang.interpreter.registry;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 从输入读取函数
 */
public class listen extends function {
    /**
     * 函数名{@value}
     */
    public static final String name="Listen";
    /**
     * 用于中间脚本的符号{@value}
     */
    public static final String silence="Silence";
    /**
     * 用于中间脚本的符号{@value}
     */
    public static final String branch="Branch";
    /**
     * 用于中间脚本的符号{@value}
     */
    public static final String defaults="Default";
    /**
     * 输入的最长沉默时间（超时后输入结束）。
     */
    private int silenceLimit;
    /**
     * 由源文件构造中间脚本时给到的字符串数组下一个分析的字符串的下标。
     */
    private Integer index=0;

    /**
     * 输入识别到的结果（来自自然语言处理的结果）对应的（散列到）跳转到的步骤名。
     */
    HashMap<String,String> toRecongnize=new HashMap<>();

    /**
     * @return 跳转到的步骤 或 输入失败 或 自然语言分析失败
     * @inheritDoc
     */
    @Override
    public String exe(globalInfo globalInfo) {


        String toGo= globalInfo.getIn().gets(silenceLimit*1000,toRecongnize.keySet()).getInfo();
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

    /**
     * @inheritDoc
     */
    @Override
    public function buildByJson(JSONObject jsonObject) {
        listen func=new listen();
        func.silenceLimit=jsonObject.getInt(registry.silenceLimt);
        for(String str: jsonObject.getJSONObject(registry.goTo).keySet()){
            func.toRecongnize.put(str,jsonObject.getJSONObject(registry.goTo).getString(str));
        }
        return func;
    }

    /**
     * @inheritDoc
     */
    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(registry.goTo,new JSONObject());
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


    /**
     * 将输入的字符串序列中的Branch/Silence/Default结构转化为JSONObject格式添加到JsonObject上
     * <br>调用后面的manageBranch和manageSilenceDefault函数。
     * @param jsonObject buildJson中要构造的JSONObject
     * @param input 输入的字符串序列
     * @return 返回转化是否成功，成功则返回true，否则返回false
     */
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

    /**
     * 将输入的字符串序列中的Silence/Default结构转化为JSONObject格式添加到JsonObject上
     * @param jsonObject buildJson中要构造的JSONObject
     * @param input 输入的字符串序列
     * @return 返回转化是否成功，成功则返回true，否则返回false
     */
    private boolean manageSilenceDefault(JSONObject jsonObject,ArrayList<String>input){
        boolean ok=false;
        if(jsonObject.has(input.get(index))) {
            globalSetting.log.warning("The Listen Already has a Step id " + jsonObject.get(input.get(index)) + " for " + input.get(index));
            return ok;
        }
        word w = new word(input.get(index));
        if(silence.equals(w.getInfo()))
            w.setInfo(registry.listenSilence);
        if(defaults.equals(w.getInfo()))
            w.setInfo(registry.listenDefault);
        index++;
        if(index>=input.size())
            globalSetting.log.warning("expected a Step Id but got nothing");
        else if(new word(input.get(index)).getType()!=word.Type.id)
            globalSetting.log.warning("expected a Step Id but got "+input.get(index));
        else {
            jsonObject.getJSONObject(registry.goTo).put(w.getInfo(),input.get(index));
            ok=true;
        }
        return ok;
    }
    /**
     * 将输入的字符串序列中的Branch结构转化为JSONObject格式添加到JsonObject上
     * @param jsonObject buildJson中要构造的JSONObject
     * @param input 输入的字符串序列
     * @return 返回转化是否成功，成功则返回true，否则返回false
     */
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

    /**
     * @return 若listen函数的跳转Step中有未声明的，则报错并返回true，否则返回false
     * @inheritDoc
     */
    @Override
    public boolean hasErrorByJson(JSONObject func, JSONObject executor) {
        boolean hasError=false;
        for(String str: func.getJSONObject(registry.goTo).keySet()){
            String id= func.getJSONObject(registry.goTo).getString(str);
            if(!executor.has(id)){
                globalSetting.log.warning("Error : "+" unknown step "+id+" on listen function detected.\n");
                hasError=true;
            }
        }
        return hasError;
    }

    /**
     * @return true，listen函数可以且只能作为一个步骤的最后一个函数。
     * @inheritDoc
     */
    @Override
    public boolean canBeEndFunction() {
        return true;
    }
    /**
     * @return false，listen函数后面不能跟着更多函数。
     * @inheritDoc
     */
    @Override
    public boolean canBeNotEndFunction() {
        return false;
    }
}
