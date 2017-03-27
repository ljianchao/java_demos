package cn.jc.demos.concurrency.lockdemo;

import cn.jc.demos.concurrency.LifeCycleListener;
import com.sun.management.ThreadMXBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/6/7.
 */
public class QueueConsumer implements LifeCycleListener{

    private boolean started = false;
    private CountDownLatch countDownLatch;
    private List<Thread> consumerThreads = new ArrayList<Thread>();

    public void start() {
        started = true;
        countDownLatch = new CountDownLatch(2);
        createConsumer();
        System.out.println("all consumers has been started.");
    }

    public void shutdown() {
        started = false;
        interruptWaitingConsumerThread();
        DestoryConsumer();
        interruptConsumerThread();
        System.out.println("all consumers has been shutdown.");
    }

    private void createConsumer(){
        for (int i = 0; i < 2; i++){
            Thread thread = new Thread(createConsumerCommand(i));
            thread.start();
            consumerThreads.add(thread);
        }
    }

    private Runnable createConsumerCommand(final int index){
        Runnable command = new Runnable() {
            public void run() {
                Thread.currentThread().setName("consumer".concat(String.valueOf(index)));
                try {
                    while (started){
                        System.out.println(Thread.currentThread().getName().concat("-").concat(SharedQueue.sharedQueue.take()));
                    }

                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName().concat(" consumer has stopped"));
                }

                if (countDownLatch != null){
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName().concat(" has count down"));
                }
            }
        };

        return command;
    }

    private void DestoryConsumer(){
        if (countDownLatch != null){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void interruptWaitingConsumerThread(){
        if (consumerThreads != null && !consumerThreads.isEmpty()){
            for (Thread consumerThread : consumerThreads) {
                if (consumerThread.getState().equals(Thread.State.WAITING)){
                    consumerThread.interrupt();
                }
            }
        }
    }

    private void interruptConsumerThread(){
        if (consumerThreads != null && !consumerThreads.isEmpty()){
            for (Thread consumerThread : consumerThreads) {
                if (consumerThread.getState().equals(Thread.State.WAITING)){
                    consumerThread.interrupt();
                }
            }
        }
    }
}
