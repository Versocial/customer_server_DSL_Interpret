package vlang.io.easyIo;

import vlang.io.analyzedInput;
import vlang.io.easyIo.easyRowInput;
import vlang.io.nlp.NLP;

import java.util.ArrayList;

public class easyNLP implements NLP<easyRowInput> {
    @Override
    public analyzedInput analyzeResult(easyRowInput input, ArrayList<String> targets) {
        String ans="Default";
        if(input.getType()!="String")
            ans="Null";
        else {
            for(String  target:targets ){
                if(input.getString().contains(target)){
                    ans=target;
                    break;
                }
            }
        }
        return new analyzedInput(ans);
    }
}
