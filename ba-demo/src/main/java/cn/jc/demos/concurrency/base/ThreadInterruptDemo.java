package cn.jc.demos.concurrency.base;

/**
 * 线程中断的示例
 * 如果被调用的线程中使用了wait()或者sleep()方法，
 * 必须使用Thread.interrupt才能中断运行的线程，实现优雅的退出线程
 *
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // 必须判断中断，否则线程会一直循环下去，导致调用线程无法退出
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("线程=" + Thread.currentThread().getName() + " interrupted!");
                        break;
                    }
                    System.out.println("线程=" + Thread.currentThread().getName() + " is running.");
                    // 会让出cpu执行时间给其他线程执行
                    Thread.yield();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程=" + Thread.currentThread().getName() + " interrupted!");
                        break;
                    }

                    System.out.println("线程=" + Thread.currentThread().getName() + " is running.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("线程=" + Thread.currentThread().getName() + " interrupted when sleep.");
                        // 捕获异常后，会清除中断标志，
                        // 为了保证数据的一致性和完整性，我们必须重设中断标志
                        Thread.currentThread().interrupt();
                    }
                    // 会使当前线程让出cpu，和其他线程同时去争夺cpu资源，但是能否再次被分配到，就不一定了
                    Thread.yield();
                }
            }
        };

        t1.start();
        t2.start();
        Thread.sleep(1000);
        // 设置线程实例t1的中断标志
        t1.interrupt();
        Thread.sleep(1000);
        // 设置线程实例t1的中断标志
        t2.interrupt();

    }
}
