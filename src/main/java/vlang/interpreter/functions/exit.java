package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import java.util.ArrayList;

public class exit extends function {
    public static final String name="Exit";
    @Override
    public String exe(globalInfo customerInfo) {
        globalSetting.log.info("exit.");
        return registry.exit;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        return new exit();
    }

    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        return new JSONObject();
    }
}
