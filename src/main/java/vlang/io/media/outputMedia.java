package vlang.io.media;

/**
 * 输出媒体。例如可以是一个语音合成并输出的设备，也可以如{@link vlang.io.easyIo.easyOutputMedia}是stdout。
 */
public interface outputMedia {
   /**
    * 通过媒体输出
    * @param output 待转化输出的字符串。
    */
   public void puts(String output);
}
