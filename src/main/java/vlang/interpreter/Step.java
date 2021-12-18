package vlang.interpreter;

import java.util.ArrayList;

/**
 * 该类是执行器{@link executor}的步骤类：
 * 一个步骤包括顺序执行的多个函数。
 */
public class Step{
    /**
     * name字段是步骤类的名称，也是区分不同步骤的唯一标识。
     */
    private   String name;
    /**
     * functions字段是该步骤中待顺序执行的各个字段。
     */
    private ArrayList<function> functions=new ArrayList<>();
    /**
     * globalInfo字段是该步骤执行所需要的全局信息，例如可能包含用户的姓名、账单等信息。
     */
    private globalInfo globalInfo;

        /**
         * 构造函数
         * @param name 步骤名
         * @param globalInfo 全局信息
         */
        public Step(String name,globalInfo globalInfo){
            this.name=name;
            this.globalInfo =globalInfo;
        }

    /**
     * 添加函数到步骤最后，在构造该类实例时使用。
     * @param func 待添加的函数
     */
    public void  addFunction(function func){
            functions.add(func);
        }

    /**
     * 执行步骤
     * @return 返回下一个应该执行的步骤名。
     */
    public String exe(){
            String stepTogo =registry.goOn;
            for(int i=0;i<functions.size();i++){
                stepTogo =functions.get(i).exe(globalInfo);
                if(stepTogo !=registry.goOn)
                    break;
            }
            return stepTogo;
        }

    /**
     * 步骤名
     * @return 返回步骤名
     */
    public String name(){
            return name;
        }
 }

