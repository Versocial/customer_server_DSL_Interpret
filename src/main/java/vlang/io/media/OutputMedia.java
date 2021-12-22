package vlang.io.media;

import vlang.io.easyIo.EasyOutputMedia;
import vlang.io.vlangIOException;

/**
 * 输出媒体。例如可以是一个语音合成并输出的设备，也可以如{@link EasyOutputMedia}是stdout。
 */
public interface OutputMedia {
    /**
     * 通过媒体输出
     *
     * @param output 待转化输出的字符串。
     */
    public void puts(String output);

    /**
     * 启动
     */
    public void open() throws vlangIOException;

    /**
     * 关闭
     */
    public void close() throws vlangIOException;
}
