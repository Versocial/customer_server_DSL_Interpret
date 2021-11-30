package vlang.interpreter.functions;

import vlang.interpreter.function;
import vlang.interpreter.globalInfo;

public class askManualService implements function {

    @Override
    public String name() {
        return "Manual";
    }

    @Override
    public String exe(globalInfo customerInfo) {
        return null;
    }
}
