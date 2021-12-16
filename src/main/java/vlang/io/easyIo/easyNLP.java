package vlang.io.easyIo;

import vlang.interpreter.registry;
import vlang.io.analyzedInput;
import vlang.io.easyIo.easyRowInput;
import vlang.io.nlp.NLP;

import java.util.ArrayList;
import java.util.Set;

public class easyNLP implements NLP<easyRowInput> {
    @Override
    public analyzedInput analyzeResult(easyRowInput input, Set<String> targets) {
        String ans=registry.listenDefault;
        if(input.getType()!="String")
            ans= registry.listenFailure;
        else {
            if(input.getString().isBlank())
                ans=registry.listenSilence;
            else {
                for (String target : targets) {
                    if (input.getString().contains(target)) {
                        ans = target;
                        break;
                    }
                }
            }
        }
          return new analyzedInput(ans);
    }
}
