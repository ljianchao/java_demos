package cn.jc.demos.concurrency.lockdemo;

import java.io.IOException;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MainClass {
    public static void main(String[] args) {
        /*final BlockQueue queue = new BlockQueue();

        new Thread() {
            @Override
            public void run() {
                try {
                    Object o= queue.pop();
                    System.out.println(o.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        queue.put("new item");*/

        // consumer
        QueueConsumer queueConsumer = new QueueConsumer();
        queueConsumer.start();

        new Thread(){
            @Override
            public void run() {
                int i = 0;
                // producer
                while (true){
                    if (i > 10)
                        break;
                    try {
                        String message = "num = ".concat(String.valueOf(i));
                        SharedQueue.sharedQueue.put(message);
                        System.out.println("producer ".concat(message));
                    } catch (InterruptedException e) {
                        System.out.println("producer has a InterruptedException");
                        e.printStackTrace();
                    }
                    i++;

                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queueConsumer.shutdown();

        System.out.println("app is stop");

    }
}
