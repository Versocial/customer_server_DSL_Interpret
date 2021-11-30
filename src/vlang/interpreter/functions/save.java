package vlang.interpreter.functions;

import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

public class save implements function {
    @Override
    public String name() {
        return "Save";
    }

    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }
}
