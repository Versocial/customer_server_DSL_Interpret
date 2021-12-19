package vlang.interpreter;

import java.util.ArrayList;

/**
 * 该类是执行器{@link executor}的步骤类：
 * 一个步骤包括顺序执行的多个函数。
 */
public class step {
    /**
     * name字段是步骤类的名称，也是区分不同步骤的唯一标识。
     */
    private   String name;
    /**
     * functions字段是该步骤中待顺序执行的各个字段。
     */
    private ArrayList<function> functions=new ArrayList<>();


        /**
         * 构造函数
         * @param name 步骤名
         */
        public step(String name){
            this.name=name;
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
     * @param globalInfo 全局信息，包括用户信息、输入输出媒介等。
     * @return 返回下一个应该执行的步骤名。
     */
    public String exe(globalInfo globalInfo){
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

