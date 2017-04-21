package cn.jc.design;

import cn.jc.demos.concurrency.future.Client;
import cn.jc.demos.concurrency.future.Data;
import cn.jc.demos.concurrency.future.FutureRealData;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/4/20.
 */
public class FutureTest{

    @Test
    public void testFuture(){
        String queryStr = "name";
        long startTime = System.currentTimeMillis();
        Client client = new Client();
        Data data = client.request(queryStr);

        // 模拟其他工作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = data.getResult();
        long interval = System.currentTimeMillis() - startTime;
        System.out.println("interval time is " + interval);

        Assert.assertEquals(queryStr, result);

    }

    @Test
    public void testJdkFuture(){
        String queryStr = "name";
        FutureTask<String> future = new FutureTask<String>(new FutureRealData(queryStr));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        long startTime = System.currentTimeMillis();
        executor.submit(future);

        // 模拟其他工作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = null;
        try {
            result = future.get(6000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        long interval = System.currentTimeMillis() - startTime;
        System.out.println("interval time is " + interval);

        Assert.assertEquals(queryStr, result);
    }
}
