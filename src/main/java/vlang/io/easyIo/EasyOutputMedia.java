package vlang.io.easyIo;

import vlang.io.media.OutputMedia;

/**
 * 简单的{@link OutputMedia}的实现，输出到stdout
 */
public class EasyOutputMedia implements OutputMedia {
    /**
     * 输出句子
     *
     * @param output 待输出的句子
     */
    @Override
    public void puts(String output) {
        System.out.println(output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
    }
}
