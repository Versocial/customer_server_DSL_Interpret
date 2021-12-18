package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import java.util.ArrayList;

/**
 * 退出函数
 */
public class exit extends function {
    /**
     * 函数名{@value}
     */
    public static final String name="Exit";

    /**
     * @return 返回值为表示 退出执行器执行方法 的字符串。
     * @inheritDoc
     */
    @Override
    public String exe(globalInfo globalInfo) {
        globalSetting.log.info("exit.");
        return registry.exit;
    }

    /**
     * @inheritDoc
     */
    @Override
    public function buildByJson(JSONObject jsonObject) {
        return new exit();
    }
    /**
     * @inheritDoc
     */
    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        return new JSONObject();
    }
    /**
     * @return false:没有错误
     * @inheritDoc
     */
    @Override
    public boolean hasErrorByJson(JSONObject func, JSONObject executor) {
        return false;
    }
    /**
     * @return true：exit可以且必须作为一个步骤的最后一个函数
     * @inheritDoc
     */
    @Override
    public boolean canBeEndFunction() {
        return true;
    }
    /**
     * @return false：exit可以不作为一个步骤的函数却不是结尾
     * @inheritDoc
     */
    @Override
    public boolean canBeNotEndFunction() {
        return false;
    }


}
