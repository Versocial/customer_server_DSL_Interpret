package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

public class speak extends function {
    public static final String name="Speak";

    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        speak func=new speak();
        return func;
    }
}
