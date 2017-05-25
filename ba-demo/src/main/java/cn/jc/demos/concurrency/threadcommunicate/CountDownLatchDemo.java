package cn.jc.demos.concurrency.threadcommunicate;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/5/25.
 */
public class CountDownLatchDemo {

    public static void runWorkers() throws InterruptedException {
        int totalThread = 3;
        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        for (int i = 0; i < totalThread; i++){
            final String threadName = "Thread" + i;
            new Thread(() -> {
                System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
                try {
                    Thread.sleep(1000);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
            }).start();
        }

        countDownLatch.await();
        long stop = System.currentTimeMillis();
        System.out.println(String.format("Total time: %s ms", (stop - start)));
    }
}
