package vlang.io.easyIo;

import vlang.io.media.inputMedia;

import javax.swing.plaf.metal.DefaultMetalTheme;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class easyInputMedia implements inputMedia<easyRowInput> {
    private String ans = "";
    private Semaphore timeOut=new Semaphore(0);
    private long latestTime=0;
    private Lock lockLatestTime=new ReentrantLock();
    private Scanner scanner=new Scanner(System.in);
    private BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

    @Override
    public easyRowInput gets(int silenceTime) {
        latestTime=System.currentTimeMillis();

        Thread readIn= new Thread(()->{
            try {
                while (true) {
                    boolean hasNext=false;
                    while(!hasNext) {
                        //System.out.println("before av");
                        int n= 0;
                        try {
                            n = System.in.available();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //System.out.println("hasNext: "+n);

                        hasNext=  n >0;
                        if(hasNext)
                            break;
                        //System.out.println("sleep");
                        Thread.sleep(1000);
                    }
                    //System.out.println("read");
                    //String just=(scanner.nextLine());
                   // ans+=just;
                    //System.out.println("ans:" + ans+" just:"+just);
                    lockLatestTime.lock();
                    latestTime = System.currentTimeMillis();
                    lockLatestTime.unlock();
                }
            } catch (InterruptedException e) {
                boolean hasNext=false;
                //System.out.println("finishing");
                do {
                    Stream<String>ss= bufferedReader.lines();//.forEach((s)->{ans+=s;});
                    // ans += .;
                    System.out.println(ss);
                    System.out.println( ss.count());

                    ss.forEach((s -> {
                        System.out.println(s);
                        ans+=s;}));
                    System.out.println("cool");
                }
                while (hasNext);
                System.out.println("finished");
                return;
            }

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
        try {
            readIn.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ans="+ans);
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
