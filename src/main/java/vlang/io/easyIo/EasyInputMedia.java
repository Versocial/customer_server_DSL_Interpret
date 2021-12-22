package vlang.io.easyIo;

import vlang.io.media.InputMedia;
import vlang.io.vlangIOException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单的{@link InputMedia}的实现，从跳出的Jframe输入框读取，读取返回值为{@link EasyRowInput}实例
 */
public class EasyInputMedia implements InputMedia<EasyRowInput> {
    /**
     * 信号量：是否超时
     */
    private Semaphore timeOut = new Semaphore(0);
    /**
     * 最后一次输入的时间
     */
    private long latestTime = 0;
    /**
     * 锁，确保对latestTime的访问互斥
     */
    private Lock lockLatestTime = new ReentrantLock();
    /**
     * 输入框
     */
    private JFrame frame;
    /**
     * 输入框上的文本框
     */
    JTextField userText;
    /**
     * 输入框部件
     */
    JPanel panel;

    /**
     * 输入监听器，该类用于文本框在有输入时更新latestTime。
     */
    protected class insertListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            lockLatestTime.lock();
            latestTime = System.currentTimeMillis();
            lockLatestTime.unlock();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }

    /**
     * 构造函数，主要是对文本输入框的设置，包括布局界面设置和添加输入监听器{@link insertListener}两个方面。
     */
    public EasyInputMedia() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyRowInput gets(long silenceTime) {
        latestTime = System.currentTimeMillis();
        //设置JFrame可见
        frame.setVisible(true);
        //启动计时器
        new Thread(new clock(silenceTime)).start();
        //等待沉默超时
        try {
            timeOut.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ans = userText.getText();
        //清空文本框并设置为不可见
        userText.setText("");
        frame.setVisible(false);
        //返回获取的输入
        return new EasyRowInput(ans);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open() throws vlangIOException {
        frame = new JFrame("Waiting for your input...");
        frame.setSize(1000, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        userText = new JTextField(100);
        userText.setBounds(100, 20, 800, 25);
        panel.add(userText);
        userText.setVisible(true);
        Document document = new PlainDocument();
        document.addDocumentListener(new insertListener());
        userText.setDocument(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        frame.dispose();
        userText = null;
        panel = null;
        frame = null;
    }


    /**
     * 计时器，当连续沉默时间超过silenceTime时设置超时信号（timeOut设置为true）。
     */
    protected class clock implements Runnable {
        private final long silenceTime;//毫秒，最长沉默时间
        private long sleepTime;//毫秒，下一次判断是否超时前需要等待的时间

        clock(long silenceTime) {
            this.silenceTime = silenceTime;
            this.sleepTime = silenceTime;
        }

        @Override
        public void run() {
            boolean repeat = true;
            while (repeat) {
                try {
                    Thread.sleep(sleepTime);//等待后再判断是否超时，因为sleepTime以内不可能超时。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean isTimeOut = false;
                lockLatestTime.lock();
                long timeNow = System.currentTimeMillis();
                if (timeNow - latestTime >= silenceTime)//若最近输入时间距离现在超过了最长沉默时间则超时。
                    isTimeOut = true;
                else
                    sleepTime = silenceTime - (timeNow - latestTime);//否则需等待到当前的最近输入时间的silenceTime后。
                lockLatestTime.unlock();

                if (isTimeOut) {
                    timeOut.release();
                    repeat = false;
                }
            }
        }
    }


}
