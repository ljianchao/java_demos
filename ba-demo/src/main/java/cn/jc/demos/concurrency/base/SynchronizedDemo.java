package cn.jc.demos.concurrency.base;

/**
 * synchronized代码示例
 *
 * 1. 指定加锁对象
 * 2. 直接作用于实例方法：相当于对当前实例加锁，进入同步代码块前要获取当前实例的锁
 * 3. 直接作用于静态方法：相等于对当前类加锁，进入同步代码块前要获取当前类的锁
 *
 */
public class SynchronizedDemo {

    private static class AccountingSync implements Runnable {

        static AccountingSync instance = new AccountingSync();

        static int i=0;

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                // 指定加锁对象
                synchronized (instance) {
                    i++;
                    System.out.println("线程=" + Thread.currentThread().getName() + ", i=" + i);
                }
            }
        }
    }

    private static class AccountingSync2 implements Runnable {

        static int i=0;

        /**
         * 直接作用于实例方法：相当于对当前实例加锁，进入同步代码块前要获取当前实例的锁
         */
        private synchronized void increase() {
            i++;

            // 加sleep休眠，可以使用jstack命令查看多线程状态下的堆栈
            // 在传入同一对象的情况下，有1个线程处于`Timed_waiting`状态，其他线程处于`BLOCKED`状态
            // 在出入不同对象的情况下，所有线程基本都处于`Timed_waiting`状态
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                increase();
                System.out.println("线程=" + Thread.currentThread().getName() + ", i=" + i);
            }
        }
    }

    private static class AccountingSync3 implements Runnable {

        static int i=0;

        /**
         * 直接作用于静态方法：相等于对当前类加锁，进入同步代码块前要获取当前类的锁
         */
        private static synchronized void increase() {
            i++;
        }

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                increase();
                System.out.println("线程=" + Thread.currentThread().getName() + ", i=" + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1, t2;
        // 示例1
        AccountingSync sync = new AccountingSync();
        t1 = new Thread(sync);
        t2 = new Thread(sync);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("示例1的结果： i=" + AccountingSync.i);
        Thread.sleep(2000);

        // 示例2，必须传入同一个对象实例
        AccountingSync2 sync2 = new AccountingSync2();
        t1 = new Thread(sync2);
        t2 = new Thread(sync2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("示例2的结果： i=" + AccountingSync2.i);
        Thread.sleep(2000);

        // 示例2的错误运行，传入不同对象实例，此时最后数据规模要大才能看到错误的计算结果
        AccountingSync2.i = 0;
        AccountingSync2 sync2E1 = new AccountingSync2();
        AccountingSync2 sync2E2 = new AccountingSync2();
        t1 = new Thread(sync2E1);
        t2 = new Thread(sync2E2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("示例2错误运行方式的结果： i=" + AccountingSync2.i);
        Thread.sleep(2000);

        // 示例3，锁定的是类，所有不需要同一个实例
        t1 = new Thread(new AccountingSync3());
        t2 = new Thread(new AccountingSync3());
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("示例3的结果： i=" + AccountingSync3.i);

    }
}
