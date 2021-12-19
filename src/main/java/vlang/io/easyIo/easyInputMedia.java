package vlang.io.easyIo;

import vlang.io.media.inputMedia;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单的{@link inputMedia}的实现，从stdin读取，读取返回值为{@link easyRowInput}实例
 */
public class easyInputMedia implements inputMedia<easyRowInput> {
    /**
     *
     */
    private Semaphore timeOut=new Semaphore(0);
    private long latestTime=0;
    private Lock lockLatestTime=new ReentrantLock();
    private JFrame frame = new JFrame("Waiting for your input...");
    JTextField userText = new JTextField(100);
    JPanel panel = new JPanel();

    protected class insertListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            lockLatestTime.lock();
            latestTime=System.currentTimeMillis();
            lockLatestTime.unlock();
            System.out.println("shit");
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
    public easyInputMedia(){
        frame.setSize(1000, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        userText.setBounds(100,20,800,25);
        panel.add(userText);
        userText.setVisible(true);
        Document document=new PlainDocument();
        document.addDocumentListener(new insertListener());
        userText.setDocument(document);

    }

    @Override
    public easyRowInput gets(long silenceTime) {//silenceTime milisecond
        latestTime=System.currentTimeMillis();

        frame.setVisible(true);

        new Thread(new clock(silenceTime )).start();

        try {
            timeOut.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String ans=userText.getText();
        System.out.println(ans);
        userText.setText("");
        frame.setVisible(false);
        return new easyRowInput(ans);
    }



    class clock implements Runnable{
        private long silenceTime;//milis
        private long sleepTime;//milis
        clock(long silenceTime){
            this.silenceTime=silenceTime;
            this.sleepTime=silenceTime;
        }
        @Override
        public void run() {
            boolean repeat=true;
            while(repeat){
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean isTimeOut=false;
                lockLatestTime.lock();
                long timeNow=System.currentTimeMillis();
                if(timeNow-latestTime>=silenceTime)
                    isTimeOut=true;
                else
                    sleepTime=silenceTime-(timeNow-latestTime);
                lockLatestTime.unlock();

                if(isTimeOut){
                    timeOut.release();
                    repeat=false;
                }
            }
        }
    }


}
