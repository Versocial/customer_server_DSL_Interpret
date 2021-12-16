package vlang.io;

/**
 * 该类是NLP分析后给出的结果
 *
 */
public class analyzedInput {
    /**
     * info 保存NLP分析后给出的结果
     */
    private final String info;

    /**
     * 构造方法
     * @param information NLP分析后给出的结果
     */
    public analyzedInput(String information){
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