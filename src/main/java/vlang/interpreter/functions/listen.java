package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

public class listen extends function {
    public static final String name="Listen";

    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        listen func=new listen();
        return func;
    }
}
