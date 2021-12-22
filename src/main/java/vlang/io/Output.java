package vlang.io;

import vlang.interpreter.Executor;
import vlang.io.media.OutputMedia;

/**
 * 该类是提供给{@link Executor}类的输出类
 */
public class Output {
    /**
     * 输出媒体
     */
    private OutputMedia outputMedia;

    /**
     * 构造方法
     *
     * @param outputMedia 输出媒体
     */
    public Output(OutputMedia outputMedia) {
        this.outputMedia = outputMedia;
    }

    /**
     * 输出方法：将给定的待输出的句子通过media输出
     *
     * @param info 要输出的句子（字符串）
     */
    public void puts(String info) {
        outputMedia.puts(info);
    }

    ;

    /**
     * 启动
     */
    public void open() throws vlangIOException {
        outputMedia.open();
    }

    /**
     * 关闭
     */
    public void close() throws vlangIOException {
        outputMedia.close();
    }
}
