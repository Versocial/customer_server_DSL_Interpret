package vlang.interpreter;

import java.util.ArrayList;

public class Step{
        private   String name;
        private ArrayList<function> functions=new ArrayList<>();
        private globalInfo globalInfo;

        public Step(String name,globalInfo globalInfo){
            this.name=name;
            this.globalInfo =globalInfo;
        }

        public void  addFunction(function func){
            functions.add(func);
        }


        public String exe(){
            String stepTogo =registry.goOn;
            for(int i=0;i<functions.size();i++){
                stepTogo =functions.get(i).exe(globalInfo);
                if(stepTogo !=registry.goOn)
                    break;
            }
            return stepTogo;
        }
 }

