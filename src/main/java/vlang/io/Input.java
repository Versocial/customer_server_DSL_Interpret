package vlang.io;

import vlang.interpreter.Executor;
import vlang.interpreter.Registry;
import vlang.io.media.InputMedia;
import vlang.io.nlp.NLP;

import java.util.Set;

/**
 * 该类是提供给{@link Executor}类的输入类，包含获取输入并进行自然语言处理的功能和保存输入的功能。
 *
 * @version 1.0
 */
public class Input {
    /**
     * NLProcessor  是该类的自然语言处理器。
     */
    private NLP NLProcessor;
    /**
     * media    是该类的输入媒体。
     */
    private InputMedia media;

    /**
     * @param NLProcessor 自然语言处理器
     * @param media       输入媒体
     */
    public Input(NLP NLProcessor, InputMedia media) {
        this.NLProcessor = NLProcessor;
        this.media = media;
    }

    /**
     * 输入和自然语言处理方法：从媒体获取输入并返回自然语言处理的结果。
     *
     * @param listenTime 导致输入结束的沉默时间，连续沉默时间超过listenTime时输入结束。
     * @param targets    需要NLP判断来自media的输入是targets中的哪个结果
     * @return NLP根据targets分析来自media的输入后得到的结果：NLP分析结果应该是targets中的一个元素或其他特殊结果：如输入失败、输入为silence等。
     */
    public AnalyzedInput gets(long listenTime, Set<String> targets) {
        AnalyzedInput ans = new AnalyzedInput(Registry.listenDefault);
        ans = NLProcessor.analyzeResult(media.gets(listenTime), targets);
        return ans;
    }

    /**
     * 启动
     */
    public void open() {
        media.open();
        NLProcessor.init();
    }

    /**
     * 关闭
     */
    public void close() {
        NLProcessor.close();
        media.close();
    }

}
