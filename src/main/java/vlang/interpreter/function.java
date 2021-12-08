package vlang.interpreter;

import org.json.JSONObject;

import java.util.ArrayList;

public abstract class function {

    public abstract String exe(globalInfo customerInfo);
    public abstract function buildByJson(JSONObject jsonObject);
    public abstract JSONObject buildJson(ArrayList<String> input);
}
