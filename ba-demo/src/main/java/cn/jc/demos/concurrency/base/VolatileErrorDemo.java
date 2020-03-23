package cn.jc.demos.concurrency.base;

/**
 * volatile的错误示例，volatile并不能代替锁，它也无法保证保证一些复合操作的原子性。
 * i++是个复合操作
 */
public class VolatileErrorDemo {

    static volatile int i = 0;

    public static class PlusTask implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                // 非原子操作
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        // 期望i最后为100000，但是总会小于100000
        for (int j=0; j < 10; j++) {
            threads[j] = new Thread(new PlusTask());
            threads[j].start();
        }

        for (int j=0; j < 10; j++) {
            try {
                // thread.join()本质上是让调用线程wait()在当前线程实例上
                // thread.join()会响应线程中断，抛出InterruptedException异常
                threads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(i);
    }
}
