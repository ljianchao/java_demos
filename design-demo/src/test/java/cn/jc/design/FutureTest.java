package cn.jc.design;

import cn.jc.demos.concurrency.future.Client;
import cn.jc.demos.concurrency.future.Data;
import cn.jc.demos.concurrency.future.FutureRealData;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/4/20.
 */
public class FutureTest extends TestCase{

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
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long interval = System.currentTimeMillis() - startTime;
        System.out.println("interval time is " + interval);

        Assert.assertEquals(queryStr, result);
    }
}
