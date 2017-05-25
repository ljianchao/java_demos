package cn.jc.demos.concurrency.threadcommunicate;

import java.util.concurrent.Phaser;

/**
 * Created by Administrator on 2017/5/25.
 */
public class PhaserDemo {
    public static void runWorkers(){
        int parties = 3;
        int phases = 4;

        final Phaser phaser = new Phaser(parties){

            @Override
            protected boolean onAdvance(int phase, int registeredParties){
                System.out.println("================= Phase: " + phase + "=======" + "Thread " + Thread.currentThread().getName());
                return registeredParties == 0;
            }
        };

        for (int i = 0; i < parties; i++){
//            int threadId = i;
            final String threadName = "Thread" + i;
            Thread.currentThread().setName(threadName);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int phase = 0; phase < phases; phase++){
                        System.out.println(String.format("Thread %s, phase %s", Thread.currentThread().getName(), phase));
                        phaser.arriveAndAwaitAdvance();
                    }
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
