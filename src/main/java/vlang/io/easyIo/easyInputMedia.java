package vlang.io.easyIo;

import vlang.io.media.inputMedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class easyInputMedia implements inputMedia<easyRowInput> {
    private String ans = "";
    private Semaphore timeOut=new Semaphore(0);
    private long latestTime=0;
    private Lock lockLatestTime=new ReentrantLock();
    private BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

    @Override
    public easyRowInput gets(int silenceTime) {
        latestTime=System.currentTimeMillis();

        Thread readIn= new Thread(()->{
            try {
                while (true) {
                    String append=bufferedReader.readLine();
                    if(Thread.interrupted())
                        break;
                    lockLatestTime.lock();
                    ans+=append;
                    latestTime = System.currentTimeMillis();
                    lockLatestTime.unlock();
                }
            }catch (IOException e){
                System.out.println("io stopped.");
                return;
            }
            System.out.println("io interrupted.");
        });
        new Thread(new clock(silenceTime * 1000L)).start();

        readIn.start();
        int a=0;
        try {
            timeOut.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        readIn.interrupt();
        String ansInput;
        lockLatestTime.lock();
            System.out.println("ans="+ans);
        try {
            System.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ansInput=ans;
        lockLatestTime.unlock();
        return new easyRowInput(ansInput);
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
                long timeNow=System.currentTimeMillis();

                lockLatestTime.lock();
                if(timeNow-latestTime>=silenceTime-10)
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
