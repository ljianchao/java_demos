package cn.jc.demos.concurrency.lockdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore示例
 *
 */
public class SemaphoreDemo implements Runnable{

    // 申请5个许可，保证每组有5个输出
    final Semaphore semaphore = new Semaphore(5);


    @Override
    public void run() {

        try {
            semaphore.acquire();
            // 模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":done!");
            semaphore.release();
            // 阻塞线程无法执行完成
//            while (true) {
//                Thread.sleep(1000);
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semaphoreDemo);
        }

        // 使用Thread创建的实例对象，run()方法运行完成线程自动释放
        // 使用线程池创建的Thread实例对象，run()方法运行完成会处于"WAITING (parking)"状态，
        // 所有必须显示关闭，否则阻塞主线程
        executorService.shutdown();

        if (!executorService.awaitTermination(20, TimeUnit.SECONDS)) {
            System.out.println("到达线程池指定等待时间，关闭线程池");
            executorService.shutdownNow();
        }

    }
}
