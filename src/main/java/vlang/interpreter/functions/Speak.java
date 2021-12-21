package vlang.interpreter.functions;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.GlobalSetting;
import vlang.interpreter.Function;
import vlang.interpreter.GlobalInfo;
import vlang.interpreter.Registry;
import vlang.interpreter.parsers.Word;

import java.util.ArrayList;

/**
 * 输出函数，向input输出指定的信息。
 */
public class Speak extends Function {
    /**
     * 函数名{@value}
     */
    public static final String name = "Speak";
    /**
     * 待输出的字符串序列
     */
    private final ArrayList<Word> toSpeak = new ArrayList<>();

    /**
     * @return 返回值为表示 继续在当前步骤执行 的字符串。
     * @inheritDoc
     */
    @Override
    public String exe(GlobalInfo globalInfo) {
        GlobalSetting.log.info("speak");
        StringBuilder ans = new StringBuilder();
        for (Word w : toSpeak) {
            String str = w.getInfo();
            if (w.getType() == Word.Type.var)//若为变量，则在globalInfo中查找其字符串值以输出。
                str = globalInfo.clientInfo().get(w.getInfo());
            ans.append(str);
        }
        globalInfo.getOut().puts(ans.toString());
        return Registry.goOn;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Function buildByJson(JSONObject jsonObject) {
        Speak func = new Speak();
        for (int i = 0; i < jsonObject.getJSONArray(Registry.param).length(); i++) {
            String toAdd = jsonObject.getJSONArray(Registry.param).getString(i);
            func.toSpeak.add(new Word(toAdd));
        }
        return func;
    }

    /**
     * @inheritDoc
     */
    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (String s : input)
            jsonArray.put(s);
        jsonObject.put(Registry.param, jsonArray);
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
     *
     * @inheritDoc
     */
    @Override
    public boolean canBeEndFunction() {
        return false;
    }

    /**
     * 返回true，在步骤中speak后面可以且必须跟着更多函数。
     *
     * @inheritDoc
     */
    @Override
    public boolean canBeNotEndFunction() {
        return true;
    }

}
