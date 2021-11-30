package vlang.interpreter.functions;

import org.json.JSONObject;
import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

import java.sql.Savepoint;

public class save extends function {
    public static final String name="Save";

    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }

    @Override
    public function buildByJson(JSONObject jsonObject) {
        save func=new save();
        return func;
    }
}
