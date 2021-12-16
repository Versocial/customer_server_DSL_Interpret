package vlang.io.nlp;

import vlang.io.analyzedInput;
import vlang.io.rowInput;

import java.util.Set;

/**
 * NLP接口类，提供给{@link vlang.io.input }类自然语言处理的功能。
 * @param <rowInputClass> 原始输入类，要求是{@link rowInput}类的子类
 */
public interface NLP<rowInputClass extends rowInput > {

    public abstract analyzedInput analyzeResult(rowInputClass input, Set<String> targets);
}
