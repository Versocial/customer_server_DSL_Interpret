package vlang.interpreter;

import java.util.HashMap;

public class interpreter implements Runnable {
    private HashMap<String,Step> steps=new HashMap<>();
    private String entryStep=null;
    private globalInfo customerInfo=null;



    public final static String entry="@entry";
    public final static String func="function";
    public final static String goTo="goto";
    public final static String goOn="goon";
    public final static String exit="exit";
    public final static String param="param";


    public interpreter(globalInfo customerInfo){
        this.customerInfo=customerInfo;
    }


    @Override
    public void run() {
        String stepTogo=entryStep;
        do{
            stepTogo=steps.get(stepTogo).exe();
        }while (stepTogo!=exit);
    }


}

