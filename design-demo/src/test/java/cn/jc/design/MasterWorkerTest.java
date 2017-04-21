package cn.jc.design;

import cn.jc.demos.concurrency.masterworker.Master;
import cn.jc.demos.concurrency.masterworker.PlusWorker;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/21.
 */
public class MasterWorkerTest {

    @Test
    public void testPlusWorker(){
        Master master = new Master(new PlusWorker(), 5);
        for (int i = 0; i < 100; i++)
            master.submit(i);
        master.execute();
        int re = 0;
        Map<String, Object> resultMap = master.getResultMap();
        // 无须等待所有worker都执行完，即可开始计算最终结果
        while (resultMap.size() > 0 || !master.isComplete()){
            Set<String> keys = resultMap.keySet();

            String key = null;
            for (String k : keys){
                key = k;
                break;
            }

            if (key == null)
                continue;

            Integer i = (Integer)resultMap.get(key);
            if (i != null)
                re += i;

            // 移除已经被计算过的项
            resultMap.remove(key);
        }

        System.out.println("the result is " + re);
    }
}
