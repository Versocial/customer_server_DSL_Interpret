package vlang.interpreter;

import org.json.JSONObject;

public abstract class function {

    public abstract String exe(globalInfo customerInfo);
    public abstract function buildByJson(JSONObject jsonObject);
}
