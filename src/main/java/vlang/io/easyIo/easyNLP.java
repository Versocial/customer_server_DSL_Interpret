package vlang.io.easyIo;

import vlang.interpreter.registry;
import vlang.io.analyzedInput;
import vlang.io.nlp.NLP;

import java.util.Set;

/**
 * 一个简单的NLP接口的实现。<br>用简单的逻辑代替复杂的自然语言处理过程。<br>用判断字符串中是否存在以targets中某项为子串的方法。
 */
public class easyNLP implements NLP<easyRowInput> {
    /**
     * 简单的字符串处理替代自然语言处理函数的实现。
     * @param input 原始输入，待进行自然语言处理的数据
     * @param targets 自然语言处理的目标：期望自然语言处理得到一个targets中的值：
     *                <br>例如，targets可能为{"投诉","账单查询"}，则期望自然语言处理得出input是在投诉还是在进行账单查询（也可能是沉默后监听失败）。
     * @return 返回值为自然语言处理的结果。
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
