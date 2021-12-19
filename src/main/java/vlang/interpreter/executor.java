package vlang.interpreter;

import vlang.globalSetting;

import java.util.HashMap;

/**
 * vlang执行器
 */
public class executor{
    /**
     * 待执行的步骤。
     */
    private HashMap<String, step> steps=new HashMap<>();
    /**
     * 入口步骤，即执行器开始执行的第一个步骤。
     */
    private String entryStep=registry.exit;


    /**
     * 构造函数
     */
    public executor(){
    }

    /**
     * 设置执行器的入口步骤，在{@link parser}的parse方法中使用。
     * @param entryStep 入口步骤的步骤名。
     */
    public void setEntryStep(String entryStep) {
        this.entryStep = entryStep;
    }

    /**
     * 执行器的执行方法。
     * @param globalInfo 全局信息，例如用户的个人信息（id、账单、姓名等），当天的时间等。
     */
    public Runnable runner(globalInfo globalInfo) {
        return new Runnable() {
            @Override
            public void run() {
                String stepTogo=entryStep;
                while (stepTogo!=registry.exit){
                    if(!steps.containsKey(stepTogo)){
                        globalSetting.log.warning("exit when unknown step: "+stepTogo);
                        break;
                    }
                    globalSetting.log.info("enter step: "+stepTogo);
                    stepTogo=steps.get(stepTogo).exe(globalInfo);
                };
            }
        };
    }

    /**
     * 添加待执行的步骤。
     * @param step 待添加的步骤。
     */
    public void addStep(step step) {
        steps.put(step.name(),step);
    }
}

