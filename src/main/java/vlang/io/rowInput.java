package vlang.io;

/**
 * 该接口是{@link vlang.io.media.inputMedia}获取的输入，是给 {@link vlang.io.nlp.NLP} 进行自然语言处理提供的原始输入。
 */
public interface rowInput {
    /**
     * 获取原始输入数据
     * @return 原始输入转化的字符串
     */
    public String getString();

    /**
     * 获取原始输入的类型
     * @return 原始输入的类型，<br>例如返回“map3”可能表示获取的原始输入是mp3文件
     *              <br>“String”则表示输入是一个String类实例。
     */
    public String getType() ;
}
