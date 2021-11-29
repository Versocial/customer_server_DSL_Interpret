package vlang.io.easyIo;

import vlang.io.rowInput;

public class easyRowInput implements rowInput {
    String information;

    public easyRowInput(String information) {
        this.information = information;
    }


    @Override
    public String getString() {
        return information;
    }

    @Override
    public String getType() {
        return "String";
    }
}
