package vlang.interpreter;

import vlang.globalSetting;
import vlang.io.input;
import vlang.io.output;

public class globalInfo {
    clientInfo clientInfo;
    input in;
    output out;

    public globalInfo(){
        clientInfo= new clientInfo();
        in = new input(globalSetting.NLprocessor,globalSetting.inMedia);
        out =new output(globalSetting.outMedia);
    }

    static class clientInfo {
        String ID;
        String name;
        String bill;
        String birth;
    }

    public output getOut(){
        return this.out;
    }

    public input getIn() {
        return in;
    }
}
