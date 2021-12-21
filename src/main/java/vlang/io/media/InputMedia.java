package vlang.io.media;

import vlang.io.Input;
import vlang.io.RowInput;

/**
 * 原始输入类，提供给{@link Input}读取输入的媒体。
 *
 * @param <rowInputClass> 该类从媒体读取输入后数据的返回值的类型。
 *                        <br>例如：<br>recordMedia&lt;mp3RowInput&gt;可能实现为一个通过媒体（录音器）录音并转化为mp3格式文件的类。
 *                        <br>recordMedia&lt;mp4RowInput&gt;可能实现为一个通过媒体（录像设备）录制视频并转化为mp4格式文件的类。
 *                        <br>easyMedia&lt;easyRowInput&gt;这里实现为一个通过标准输入输入并转化为String类型字符串的类。
 */
public interface InputMedia<rowInputClass extends RowInput> {
    /**
     * 从媒体获取输入
     *
     * @param silenceTime 当媒体经过连续silenceTime 毫秒 时间没有输入时输入结束
     * @return 返回获取的原始输入。
     */
    public rowInputClass gets(long silenceTime);

    /**
     * 启动
     */
    public void open();

    /**
     * 关闭
     */
    public void close();
}
