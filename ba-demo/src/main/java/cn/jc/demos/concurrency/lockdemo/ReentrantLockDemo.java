package cn.jc.demos.concurrency.lockdemo;

import javafx.animation.Timeline;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁示例
 * 开发人员决定何时加锁，何时解锁，重入锁对逻辑控制的灵活性要远远好于synchronized。
 * 重入锁可以中断响应，可以用来解决死锁
 */
public class ReentrantLockDemo {

    /**
     * 展示重入锁ReentrantLock的灵活性
     *
     */
    private static class ReenterLock implements Runnable {

        static ReentrantLock lock = new ReentrantLock();

        static int i = 0;

        @Override
        public void run() {
            for (int j=0; j < 1000000; j++) {
                // 通过jstack查看堆栈发现未获取锁的线程状态是`WAITING (parking)`，使用LockSupport.park（）实现
                lock.lock();
                try {
                    i++;
                    System.out.println("线程=" + Thread.currentThread().getName() + ", i=" + i);
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                } finally {
                    // 手动释放锁
                    lock.unlock();
                }
            }

            System.out.println(Thread.currentThread().getName() + "： 线程退出");
        }
    }

    /**
     * 重入锁的中断响应，解决死锁现象
     *
     */
    private static class IntLock implements Runnable {

        static ReentrantLock lock1 = new ReentrantLock();
        static ReentrantLock lock2 = new ReentrantLock();

        int lock;

        /**
         * 控制加锁顺序，方便构造死锁
         * @param lock
         */
        public IntLock(int lock) {
            this.lock = lock;
        }

        /**
         * 发生死锁时，两个线程的都处于`WAITING (parking)`
         */
        @Override
        public void run() {

            try {
                if (this.lock == 1) {
                    lock1.lockInterruptibly();
                    System.out.println("线程=" + Thread.currentThread().getName() + ", 获取lock1的锁.");
                    // 等待时间，确保另一线程先获取lock2锁
                    try {

                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }

                    lock2.lockInterruptibly();
                    System.out.println("线程=" + Thread.currentThread().getName() + ", 获取lock2的锁.");
                } else {
                    lock2.lockInterruptibly();
                    System.out.println("线程=" + Thread.currentThread().getName() + ", 获取lock2的锁.");
                    // 等待时间，确保另一线程先获取lock1锁
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }

                    lock1.lockInterruptibly();
                    System.out.println("线程=" + Thread.currentThread().getName() + ", 获取lock1的锁.");
                }
            } catch (InterruptedException e) {
                System.out.println("线程=" + Thread.currentThread().getName() + "，收到中断标识：");
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }

                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }

                System.out.println(Thread.currentThread().getName() + "： 线程退出");
            }

        }
    }

    /**
     * 锁申请等待限时
     *
     * 如果tryLock()方法不带参数直接运行，当前线程会尝试获取锁，如果锁并未被其他线程占用，则申请锁会成功，并立即返回true，
     * 如果锁被其他线程占用，则当前线程不会进行等待，而是立即返回false。
     *
     */
    private static class TimeLock implements Runnable {

        static ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println("线程=" + Thread.currentThread().getName() + ", get lock successed.");
                    Thread.sleep(6000);
                } else {
                    System.out.println("线程=" + Thread.currentThread().getName() + ", get lock failed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 只有成功获取锁的线程才能释放锁
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }

                System.out.println(Thread.currentThread().getName() + "： 线程退出");
            }
        }
    }

    /**
     * 公平锁
     * 实现公平锁系统会维护一个有序队列，因此公平锁的实现成本较高，性能相对也非常低下
     * 多个线程基本是交替获得锁
     *
     */
    private static class FairLock implements Runnable {

        // 传入参数true，表示使用公平锁
        static ReentrantLock fairlock = new ReentrantLock(true);
        static int i = 0;

        @Override
        public void run() {
            while (i <= 30) {

                try {
                    // 不能使用fairlock.tryLock()，否则不会进行排队
                    fairlock.lock();
                    i++;
                    System.out.println("线程=" + Thread.currentThread().getName() + ", get lock successed.");
                } finally {
                    fairlock.unlock();
                }

            }
        }
    }

    /**
     * 重入锁 + Condition条件
     *
     */
    private static class ReenterLockCondition implements Runnable {

        static ReentrantLock lock = new ReentrantLock();
        // 生成一个与当前重入锁绑定的Condition实例
        static Condition condition = lock.newCondition();

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("线程=" + Thread.currentThread().getName() + ", get lock successed.");
                System.out.println("线程=" + Thread.currentThread().getName() + ", will be waiting.");
                condition.await();
                System.out.println("线程=" + Thread.currentThread().getName() + ", is going on.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1, t2;


        // 示例1，展示重入锁ReentrantLock的灵活性
//        System.out.println("示例1[展示重入锁ReentrantLock的灵活性]开始.");
//        ReenterLock reenterLock = new ReenterLock();
//        t1 = new Thread(reenterLock);
//        t2 = new Thread(reenterLock);
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        System.out.println("示例1的结果： i=" + ReenterLock.i);
//        Thread.sleep(2000);

        // 示例2，展示重入锁的中断响应，构造死锁场景展示
        System.out.println("\n示例2[展示重入锁的中断响应]开始.");
        IntLock intLock1 = new IntLock(1);
        IntLock intLock2 = new IntLock(2);

        t1 = new Thread(intLock1);
        t2 = new Thread(intLock2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        // 中断其中一个线程，保证另一线程可以正常执行
        System.out.println("中断线程：" + t2.getName());
        t2.interrupt();
        Thread.sleep(2000);

        // 示例3，展示锁申请等待限时
        System.out.println("\n示例3[展示锁申请等待限时]开始.");
        TimeLock timeLock = new TimeLock();
        t1 = new Thread(timeLock);
        t2 = new Thread(timeLock);
        t1.start();
        t2.start();
        Thread.sleep(7000);

        // 示例4，展示公平锁
        System.out.println("\n示例4[展示公平锁]开始.");
        FairLock fairLock = new FairLock();
        t1 = new Thread(fairLock);
        t2 = new Thread(fairLock);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        Thread.sleep(2000);

        // 示例5，展示重入锁 + Condition条件
        System.out.println("\n示例5[展示重入锁 + Condition条件]开始.");
        ReenterLockCondition lockCondition = new ReenterLockCondition();
        t1 = new Thread(lockCondition);
        t1.start();
        Thread.sleep(1000);
        // 通知线程t1继续执行
        try {
            ReenterLockCondition.lock.lock();
            ReenterLockCondition.condition.signal();
        } finally {
            ReenterLockCondition.lock.unlock();
        }

        t1.join();
    }

}
