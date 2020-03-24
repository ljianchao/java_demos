package cn.jc.demos.concurrency.base;

import java.util.UUID;

/**
 * Java实现两个线程交替打印1-100，或者两个线程交替打印奇数和偶数
 * 实现Runnable接口
 *
 */
public class WaitNofityAlternatelyPrint {

    private static class Worker implements Runnable {

        private int i = 1;

        @Override
        public void run() {
            // this代表Worker对象的实例，若使用继承Thread类的方式，慎用this
            synchronized (this) {
                System.out.println("线程=" + Thread.currentThread().getName() + " 获取锁");
                // while循环写里面可以避免从头获取锁
                while (true) {
                    // 唤醒拥有对象锁this的一个线程去争抢对象锁this
                    // 不过需要本次运行this.wait()释放锁后，才能去抢锁
                    this.notify();

                    // 加大执行间隔，以便观测线程状态
                    try {
                        // 使用jstack查看，线程处于`TIMED_WAITING`状态
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (i > 100) {
                        // 退出循环
                        break;
                    }

                    // 增加唯一id，识别线程退出的中间输入内容
                    String id = UUID.randomUUID().toString();
                    System.out.println("线程=" + Thread.currentThread().getName() + ", id=" + id + ", i=" + i);
                    i++;

                    try {
                        // 放弃对象锁this
                        // 使用jstack查看，线程如果在未收到notify()通知前，处于`WAITING`状态
                        // 线程如果收到notify()通知，则处于`BLOCKED`状态
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("线程=" + Thread.currentThread().getName() + ", id=" + id + ", 执行完毕");
                }
            }

//            while (true) {
//                // 会一直重入锁
//                System.out.println("线程=" + Thread.currentThread().getName() + " 获取锁");
//
//                // this代表Worker对象的实例，若使用继承Thread类的方式，慎用this
//                synchronized (this) {
//
//                    // 唤醒拥有对象锁this的一个线程去争抢对象锁this
//                    // 不过需要本次运行this.wait()释放锁后，才能去抢锁
//                    this.notify();
//
//                    // 加大执行间隔，以便观测线程状态
//                    try {
//                        // 使用jstack查看，线程处于`TIMED_WAITING`状态
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    if (i > 100) {
//                        // 退出循环
//                        break;
//                    }
//
//                    // 增加唯一id，识别线程退出的中间输入内容
//                    String id = UUID.randomUUID().toString();
//                    System.out.println("线程=" + Thread.currentThread().getName() + ", id=" + id + ", i=" + i);
//                    i++;
//
//                    try {
//                        // 放弃对象锁this
//                        // 使用jstack查看，线程如果在未收到notify()通知前，处于`WAITING`状态
//                        // 线程如果收到notify()通知，则处于`BLOCKED`状态
//                        this.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("线程=" + Thread.currentThread().getName() + ", id=" + id + ", 执行完毕");
//                }
//            }

        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();

        Thread thread1 = new Thread(worker);
        Thread thread2 = new Thread(worker);
        thread1.start();
        thread2.start();

    }
}
