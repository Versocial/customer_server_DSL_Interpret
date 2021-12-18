package vlang.interpreter.functions;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import java.util.ArrayList;

/**
 * 输出函数，向input输出指定的信息。
 */
public class speak extends function {
    /**
     * 函数名{@value}
     */
    public static final String name="Speak";
    /**
     * 待输出的字符串序列
     */
    private  ArrayList<String> toSpeak=new ArrayList<>();

    /**
     * @return 返回值为表示 继续在当前步骤执行 的字符串。
     * @inheritDoc
     */
    @Override
    public String exe(globalInfo globalInfo) {
        globalSetting.log.info("speak");
        for( String str : toSpeak) {
            globalInfo.getOut().puts(str);
        }
        return registry.goOn;
    }

    /**
     * @inheritDoc
     */
    @Override
    public function buildByJson(JSONObject jsonObject) {
        speak func=new speak();
        for(int i=0;i<jsonObject.getJSONArray(registry.param).length();i++){
            func.toSpeak.add(jsonObject.getJSONArray(registry.param).getString(i));
        }
        return func;
    }

    /**
     * @inheritDoc
     */
    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<input.size();i++)
            jsonArray.put(input.get(i));
        jsonObject.put(registry.param,jsonArray);
        return jsonObject;
    }

    /**
     * @return false，表示默认没有错误。
     * @inheritDoc
     */
    @Override
    public boolean hasErrorByJson(JSONObject func, JSONObject executor) {
        return false;
    }

    /**
     * 返回false，speak不可以做步骤中最后一个函数。
     * @inheritDoc
     */
    @Override
    public boolean canBeEndFunction() {
        return false;
    }

    /**
     * 返回true，在步骤中speak后面可以且必须跟着更多函数。
     * @inheritDoc
     */
    @Override
    public boolean canBeNotEndFunction() {
        return true;
    }

}
