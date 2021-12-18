package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import java.util.ArrayList;

/**
 * 未完成的函数：请求人工服务
 */
public class askManualService extends function {
    /**
     * 函数名{@value}
     */
    public static final String name="Manual";

    /**
     * @return 终止执行器执行方法
     * @inheritDoc
     */
    @Override
    public String exe(globalInfo globalInfo) {
        return registry.exit;
    }

    /**
     * @inheritDoc
     */
    @Override
    public function buildByJson(JSONObject jsonObject) {
        askManualService func=new askManualService();
        return func;
    }

    /**
     * @inheritDoc
     */
    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        return null;
    }

    /**
     * @return false，默认没有错误
     * @inheritDoc
     */
    @Override
    public boolean hasErrorByJson(JSONObject func, JSONObject executor) {
        return false;
    }

    /**
     * @return true，该函数可以且必须作为步骤的最后一个函数。
     * @inheritDoc
     */
    @Override
    public boolean canBeEndFunction() {
        return true;
    }

    /**
     * @return false，该函数不可以作为一个步骤的中间执行（非最后一个）的函数
     * @inheritDoc
     */
    @Override
    public boolean canBeNotEndFunction() {
        return false;
    }


}
