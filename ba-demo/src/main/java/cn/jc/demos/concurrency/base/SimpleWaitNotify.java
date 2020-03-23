package cn.jc.demos.concurrency.base;

/**
 * Object.wait()和Object.notify()的简单示例
 *
 * Object.wait()和Object.notify()需配合synchronized一起使用，
 * Object.wait()会立即释放synchronized持有的对象锁
 *
 * Object.wait()会响应线程中断，抛出InterruptedException异常
 */
public class SimpleWaitNotify {

    // 常量作为共享对象
    final static Object object = new Object();

    public static class T1 extends Thread {

        @Override
        public void run() {

            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T1 start!");

                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait for object.");
                    // 会释放object对象的锁
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + ": T1 end.");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {

            // T1线程没有执行object.wait()方法释放锁之前，该代码块一直处于阻塞状态
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T2 start! nofity one thread");

                object.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end!");

                // T2线程等待2秒钟，以便观测在本线程执行完毕，释放了object锁时，
                // T1线程才能收到从新获得object锁，继续执行object.wait()后面的代码
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        // 必须先启动T2
        t1.start();
        t2.start();
    }
}
