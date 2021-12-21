package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.GlobalSetting;
import vlang.interpreter.Function;
import vlang.interpreter.GlobalInfo;
import vlang.interpreter.Registry;
import vlang.interpreter.parsers.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * 从输入读取函数
 */
public class Listen extends Function {
    /**
     * 函数名{@value}
     */
    public static final String name = "Listen";
    /**
     * 用于中间脚本的符号{@value}
     */
    public static final String silence = "Silence";
    /**
     * 用于中间脚本的符号{@value}
     */
    public static final String branch = "Branch";
    /**
     * 用于中间脚本的符号{@value}
     */
    public static final String defaults = "Default";
    /**
     * 输入的最长沉默时间（超时后输入结束）。
     */
    private int silenceLimit;
    /**
     * 由源文件构造中间脚本时给到的字符串数组下一个分析的字符串的下标。
     */
    private Integer index = 0;

    /**
     * 输入识别到的结果（来自自然语言处理的结果）对应的（散列到）跳转到的步骤名。
     */
    HashMap<String, String> targets = new HashMap<>();

    /**
     * @return 跳转到的步骤 或 输入失败 或 自然语言分析失败
     * @inheritDoc
     */
    @Override
    public String exe(GlobalInfo globalInfo) {
        //获取输入并进行自然语言处理
        String toGo = globalInfo.getIn().gets(silenceLimit * 1000L, targets.keySet()).getInfo();
        GlobalSetting.log.info("listen:" + toGo);
        //根据自然语言处理的结果返回不同意义的值
        if (Objects.equals(toGo, Registry.listenFailure))//输入失败
            return Registry.listenFailure;
        else if (targets.containsKey(toGo))//分析结果是预期的结果之一
            return targets.get(toGo);
        else if (!targets.containsKey(toGo) && targets.containsKey(Registry.listenDefault))//默认结果
            return targets.get(Registry.listenDefault);
        else
            return Registry.nlpFailure;//自然语言分析失败
    }

    /**
     * @inheritDoc
     */
    @Override
    public Function buildByJson(JSONObject jsonObject) {
        Listen func = new Listen();
        func.silenceLimit = jsonObject.getInt(Registry.silenceLimt);
        for (String str : jsonObject.getJSONObject(Registry.goTo).keySet()) {
            func.targets.put(str, jsonObject.getJSONObject(Registry.goTo).getString(str));
        }
        return func;
    }

    /**
     * @inheritDoc
     */
    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Registry.goTo, new JSONObject());
        int t = Math.max(input.size(), 2);
        Word w = new Word("@unkown");
        boolean repeat = true;//当发现错误时置为false否则为true
        for (index = 0; index < t && repeat; index++) {
            if (index < input.size())
                w = new Word(input.get(index));

            if (index == 0) { //第一个参数，期望是输入的最长沉默时间silenceTime
                if (index >= input.size()) {
                    GlobalSetting.log.warning("expected num but got nothing");
                    repeat = false;
                    continue;
                }
                if (w.getType() != Word.Type.num) {
                    GlobalSetting.log.warning("expected num but got " + w.getInfo());
                    repeat = false;
                    continue;
                }
                jsonObject.put(Registry.silenceLimt, Integer.parseInt(w.getInfo()));
            } else { //期望是Branch/Silence/Default
                if (w.getType() != Word.Type.branch) {
                    GlobalSetting.log.warning("expected 'branch' but got " + w.getInfo());
                    repeat = false;
                    continue;
                }
                repeat = gotBranch(jsonObject, input);
            }

        }
        if (!repeat)
            GlobalSetting.log.warning("Parser failed on Listen function.");
        return jsonObject;
    }


    /**
     * 将输入的字符串序列中的Branch/Silence/Default结构转化为JSONObject格式添加到JsonObject上
     * <br>调用后面的manageBranch和manageSilenceDefault函数。
     *
     * @param jsonObject buildJson中要构造的JSONObject
     * @param input      输入的字符串序列
     * @return 返回转化是否成功，成功则返回true，否则返回false
     */
    private boolean gotBranch(JSONObject jsonObject, ArrayList<String> input) {
        boolean ok;
        Word w = new Word(input.get(index));
        switch (w.getInfo()) {//分为Silence、Default分支和Branch分支两类处理。
            case silence, defaults -> ok = manageSilenceDefault(jsonObject, input);
            case branch -> ok = manageBranch(jsonObject, input);
            default -> {
                GlobalSetting.log.warning("expected 'branch' but got " + w.getInfo());
                ok = false;
            }
        }
        return ok;
    }

    /**
     * 将输入的字符串序列中的Silence/Default结构转化为JSONObject格式添加到JsonObject上
     *
     * @param jsonObject buildJson中要构造的JSONObject
     * @param input      输入的字符串序列
     * @return 返回转化是否成功，成功则返回true，否则返回false
     */
    private boolean manageSilenceDefault(JSONObject jsonObject, ArrayList<String> input) {
        boolean ok = false;
        if (jsonObject.has(input.get(index))) {//该Listen函数已经有此自然语言处理结果对应的转移分支
            GlobalSetting.log.warning("The Listen Already has a Step id " + jsonObject.get(input.get(index)) + " for " + input.get(index));
            return ok;
        }
        //设置该转移分支的自然语言处理结果
        Word w = new Word(input.get(index));
        if (silence.equals(w.getInfo()))
            w.setInfo(Registry.listenSilence);
        if (defaults.equals(w.getInfo()))
            w.setInfo(Registry.listenDefault);
        index++;
        //设置该转移分支的转移到的下一个步骤
        if (index >= input.size())
            GlobalSetting.log.warning("expected a Step Id but got nothing");
        else if (new Word(input.get(index)).getType() != Word.Type.id)
            GlobalSetting.log.warning("expected a Step Id but got " + input.get(index));
        else {
            jsonObject.getJSONObject(Registry.goTo).put(w.getInfo(), input.get(index));
            ok = true;
        }
        return ok;
    }

    /**
     * 将输入的字符串序列中的Branch结构转化为JSONObject格式添加到JsonObject上
     *
     * @param jsonObject buildJson中要构造的JSONObject
     * @param input      输入的字符串序列
     * @return 返回转化是否成功，成功则返回true，否则返回false
     */
    private boolean manageBranch(JSONObject jsonObject, ArrayList<String> input) {
        boolean ok = false;
        index++;
        if (index >= input.size())
            GlobalSetting.log.warning("expected a String but got nothing");
        else if (new Word(input.get(index)).getType() != Word.Type.string)
            GlobalSetting.log.warning("expected a String but got " + input.get(index));
        else if (jsonObject.has(input.get(index)))
            GlobalSetting.log.warning("The Listen Already has a Step id " + jsonObject.get(input.get(index)) + " for " + input.get(index));
        else
            ok = manageSilenceDefault(jsonObject, input);//处理完Branch后Branch分支的处理和Silence/Default处理方法相同，这里重用代码
        return ok;
    }

    /**
     * @return 若listen函数的跳转Step中有未声明的，则报错并返回true，否则返回false
     * @inheritDoc
     */
    @Override
    public boolean hasErrorByJson(JSONObject func, JSONObject executor) {
        boolean hasError = false;
        for (String str : func.getJSONObject(Registry.goTo).keySet()) {
            String id = func.getJSONObject(Registry.goTo).getString(str);
            if (!executor.has(id)) {//当转移分支转移到的步骤（Step）在源文件中未声明
                GlobalSetting.log.warning("Error : " + " unknown step " + id + " on listen function detected.\n");
                hasError = true;
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
