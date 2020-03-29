package cn.jc.demos.concurrency.lockdemo;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport示例
 * LockSupport类使用类似信号量的机制。它为每个线程准备了一个许可，如果许可可用，
 * 那么park()函数会立即返回，并且消费这个许可。
 *
 * 即使unpark()操作反生在park()之前，它也可以使下一次的park()操作立即返回。
 *
 */
public class LockSupportDemo {

    public static Object u = new Object();

    private static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                // 线程处于"WAITING (parking)"状态
                LockSupport.park();

                // LockSupport.park()支持中断影响，但是不会抛出InterruptedException异常，
                // 只会默默发返回，可以从Thread.interrupted()等方法获得中断标记
                if (Thread.interrupted()) {
                    System.out.println(getName() + " 被中断了");
                }
                System.out.println(getName() + "执行结束");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ChangeObjectThread t1 = new ChangeObjectThread("t1");
        ChangeObjectThread t2 = new ChangeObjectThread("t2");

        t1.start();
        Thread.sleep(100);
        t2.start();

        LockSupport.unpark(t1);
        LockSupport.unpark(t2);

        t1.join();
        t2.join();

        ChangeObjectThread t3 = new ChangeObjectThread("t3");
        ChangeObjectThread t4 = new ChangeObjectThread("t4");

        t3.start();
        Thread.sleep(100);
        t4.start();
        t3.interrupt();
        LockSupport.unpark(t4);
        t3.join();
        t4.join();
    }
}
