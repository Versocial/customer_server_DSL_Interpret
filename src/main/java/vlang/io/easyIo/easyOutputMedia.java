package vlang.io.easyIo;

import vlang.io.media.outputMedia;

/**
 * 简单的输出类，输出到stdout
 */
public class easyOutputMedia implements outputMedia {
    /**
     * 输出句子
     * @param output 待输出的句子
     */
    @Override
    public void puts(String output) {
        System.out.println(output);
    }
}
