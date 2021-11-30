package vlang.interpreter;

import java.util.HashMap;

public class executor implements Runnable {
    private HashMap<String,Step> steps=new HashMap<>();
    private String entryStep=registry.exit;
    private globalInfo globalInfo =null;



    public executor(globalInfo customerinfo){
        this.globalInfo =customerinfo;
    }

    public void setEntryStep(String entryStep) {
        this.entryStep = entryStep;
    }

    public void addStep(String name,Step code){
        steps.put(name,code);
    }

    @Override
    public void run() {
        String stepTogo=entryStep;
        while (stepTogo!=registry.exit){
            stepTogo=steps.get(stepTogo).exe();
        };
    }


}

