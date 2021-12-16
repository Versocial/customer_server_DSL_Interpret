package vlang.io;
import  vlang.io.media.outputMedia;

/**
 *  该类是提供给{@link vlang.interpreter.executor}类的输出类
 */
public class output {
    /**
     * 输出媒体
     */
    private outputMedia outputMedia;

    /**
     * 构造方法
     * @param outputMedia 输出媒体
     */
    public output(outputMedia outputMedia){
        this.outputMedia=outputMedia;
    }

    /**
     * 输出方法：将给定的待输出的句子通过media输出
     * @param info 要输出的句子（字符串）
     */
    public void puts(String info){
        outputMedia.puts(info);
    };
}
