package vlang.interpreter;

import vlang.globalSetting;
import vlang.interpreter.functions.exit;

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

    @Override
    public void run() {
        String stepTogo=entryStep;
        while (stepTogo!=registry.exit){
            if(!steps.containsKey(stepTogo)){
                globalSetting.log.info("exit when unknown step: "+stepTogo);
                break;
            }
            stepTogo=steps.get(stepTogo).exe();
        };
    }

    public void addStep(Step step) {
        steps.put(step.name(),step);
    }
}

