package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.globalSetting;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import java.sql.Savepoint;
import java.util.ArrayList;

public class save extends function {
    public static final String name="Save";

    @Override
    public String exe(globalInfo customerInfo) {
        globalSetting.log.info("save");
        return registry.goOn;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        save func=new save();
        return func;
    }

    public JSONObject buildJson(ArrayList<String> params ){
        return new JSONObject();
    }
}
