package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

public class exit extends function {
    @Override
    public String exe(globalInfo customerInfo) {
        globalSetting.log.info("exit.");
        return registry.exit;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        return new exit();
    }
}
