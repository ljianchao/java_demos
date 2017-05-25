package cn.jc.demos.concurrency.threadcommunicate;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/5/25.
 */
public class CyclicBarrierDemo {

    public static void runWorkers(){
        int totalThread = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);

        for (int i = 0; i < totalThread; i++){
            final String threadName = "Thread" + i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "is waiting"));
                    try {
                        cyclicBarrier.await();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
