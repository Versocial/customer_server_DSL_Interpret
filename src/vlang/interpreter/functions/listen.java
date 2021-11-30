package vlang.interpreter.functions;

import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

public class listen implements function {

    @Override
    public String name() {
        return "Listen";
    }

    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }
}
