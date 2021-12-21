package vlang.interpreter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 该类是执行器{@link Executor}的步骤类：
 * 一个步骤包括顺序执行的多个函数。
 */
public class Step {
    /**
     * name字段是步骤类的名称，也是区分不同步骤的唯一标识。
     */
    private final String name;
    /**
     * functions字段是该步骤中待顺序执行的各个字段。
     */
    private final ArrayList<Function> functions = new ArrayList<>();


    /**
     * 构造函数
     *
     * @param name 步骤名
     */
    public Step(String name) {
        this.name = name;
    }

    /**
     * 添加函数到步骤最后，在构造该类实例时使用。
     *
     * @param func 待添加的函数
     */
    public void addFunction(Function func) {
        functions.add(func);
    }

    /**
     * 执行步骤
     *
     * @param globalInfo 全局信息，包括用户信息、输入输出媒介等。
     * @return 返回下一个应该执行的步骤名。
     */
    public String exe(GlobalInfo globalInfo) {
        String stepTogo = Registry.goOn;//goOn表示继续执行当前步骤，否则表示结束当前步骤并退出或跳转到其他步骤。
        for (Function function : functions) {
            stepTogo = function.exe(globalInfo);
            if (!Objects.equals(stepTogo, Registry.goOn))
                break;
        }
        return stepTogo;
    }

    /**
     * 步骤名
     *
     * @return 返回步骤名
     */
    public String name() {
        return name;
    }
}

