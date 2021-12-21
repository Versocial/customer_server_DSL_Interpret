package vlang.io;

/**
 * 该类是NLP分析后给出的结果
 * <br>这里与一个String的实例作用类似，设置此类以可扩展。
 */
public class AnalyzedInput {
    /**
     * info 保存NLP分析后给出的结果
     */
    private final String info;

    /**
     * 构造方法
     * @param information NLP分析后给出的结果
     */
    public AnalyzedInput(String information){
        info=information;
    }

    /**
     *
     * @return NLP分析后给出的结果
     */
    public String getInfo() {
        return info;
    }
}