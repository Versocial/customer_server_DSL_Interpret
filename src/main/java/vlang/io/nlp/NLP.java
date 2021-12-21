package vlang.io.nlp;

import vlang.io.AnalyzedInput;
import vlang.io.Input;
import vlang.io.RowInput;

import java.util.Set;

/**
 * NLP接口类，提供给{@link Input }类自然语言处理的功能。
 * @param <rowInputClass> 原始输入类，要求是{@link RowInput}类的子类
 */
public interface NLP<rowInputClass extends RowInput> {
    /**
     * 进行自然语言处理的分析
     * @param input 原始输入，待进行自然语言处理的数据
     * @param targets 自然语言处理的目标：期望自然语言处理得到一个targets中的值：
     *                <br>例如，targets可能为{"投诉","账单查询"}，则期望自然语言处理得出input是在投诉还是在进行账单查询。
     * @return 返回自然语言处理分析后的结果。
     */
    public abstract AnalyzedInput analyzeResult(rowInputClass input, Set<String> targets);
    /**
     * 启动和初始化
     */
    public void init();

    /**
     * 关闭
     */
    public void close();
}
