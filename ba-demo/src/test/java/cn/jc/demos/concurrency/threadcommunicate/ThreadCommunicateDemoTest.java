package cn.jc.demos.concurrency.threadcommunicate;

import org.junit.Test;

/**
 * Created by Administrator on 2017/5/25.
 */
public class ThreadCommunicateDemoTest {

    @Test
    public void testCountDownRunWorkers(){
        try {
            CountDownLatchDemo.runWorkers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCyclicBarrierRunWorkers(){
        CyclicBarrierDemo.runWorkers();
    }

    @Test
    public void testPhaserRunWorkers(){
        PhaserDemo.runWorkers();
    }
}
