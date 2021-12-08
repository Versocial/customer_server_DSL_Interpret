package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;
import vlang.interpreter.registry;

import java.util.ArrayList;

public class askManualService extends function {
    public static final String name="Manual";

    public askManualService(){

    }


    @Override
    public String exe(globalInfo customerInfo) {
        return registry.goOn;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        askManualService func=new askManualService();
        return func;
    }

    @Override
    public JSONObject buildJson(ArrayList<String> input) {
        return null;
    }
}
