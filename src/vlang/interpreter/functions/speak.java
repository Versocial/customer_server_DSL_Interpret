package vlang.interpreter.functions;

import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

public class speak implements function {

    @Override
    public String name() {
        return "Speak";
    }

    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }
}
