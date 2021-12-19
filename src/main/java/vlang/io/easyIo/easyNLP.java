package vlang.io.easyIo;

import vlang.interpreter.registry;
import vlang.io.analyzedInput;
import vlang.io.nlp.NLP;

import java.util.Set;

/**
 * 一个简单的NLP接口的实现。<br>用简单的逻辑代替复杂的自然语言处理过程。<br>用判断字符串中是否存在targets中某项为子串的方法。
 */
public class easyNLP implements NLP<easyRowInput> {
    /**
     * 这里用判断字符串中是否存在targets中某项为子串替代语法分析。
     * @inheritDoc
     */
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
