package vlang.interpreter;

import java.util.ArrayList;

public class Step{
        String name;
        ArrayList<function> functions;
        globalInfo customerInfo;

        public String exe(){
            String stepTogo =interpreter.goOn;
            for(int i=0;i<functions.size();i++){
                stepTogo =functions.get(i).exe(customerInfo);
                if(stepTogo !=interpreter.goOn)
                    break;
            }
            return stepTogo;
        }
 }

