package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

public class askManualService extends function {
    public static final String name="Manual";

    public askManualService(){

    }


    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        askManualService func=new askManualService();
        return func;
    }
}
