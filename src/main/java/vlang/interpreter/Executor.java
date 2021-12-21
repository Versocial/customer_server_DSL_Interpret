package vlang.interpreter;

import vlang.GlobalSetting;

import java.util.HashMap;
import java.util.Objects;

/**
 * vlang执行器
 */
public class Executor {
    /**
     * 待执行的步骤。
     */
    private final HashMap<String, Step> steps=new HashMap<>();
    /**
     * 入口步骤，即执行器开始执行的第一个步骤。
     */
    private String entryStep= Registry.exit;


    /**
     * 构造函数
     */
    public Executor(){
    }

    /**
     * 设置执行器的入口步骤，在{@link Parser}的parse方法中使用。
     * @param entryStep 入口步骤的步骤名。
     */
    public void setEntryStep(String entryStep) {
        this.entryStep = entryStep;
    }

    /**
     * 获取某个用户、某种媒体下（用户、媒体由globalInfo决定）执行器的执行方法。返回{@link Runnable}类实例方便并发执行时使用{@link Thread}类。
     * @param globalInfo 全局信息，例如用户的个人信息（id、账单、姓名等），当天的时间，输入/输出模块等。
     */
    public Runnable runner(GlobalInfo globalInfo) {
        return () -> {
            globalInfo.start();
            String stepTogo=entryStep;
            while (!Objects.equals(stepTogo, Registry.exit)){
                if(!steps.containsKey(stepTogo)){
                    GlobalSetting.log.warning("exit when unknown step: "+stepTogo);
                    break;
                }
                GlobalSetting.log.info("enter step: "+stepTogo);
                stepTogo=steps.get(stepTogo).exe(globalInfo);
            }
            globalInfo.finish();
        };
    }

    /**
     * 添加待执行的步骤。
     * @param step 待添加的步骤。
     */
    public void addStep(Step step) {
        steps.put(step.name(),step);
    }
}

