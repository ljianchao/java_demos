package cn.jc.demos.concurrency.masterworker;

import java.util.Map;
import java.util.Queue;

/**
 * Created by Administrator on 2017/4/21.
 */
public class Worker implements Runnable {

    // 任务队列，用于取得子任务
    protected Queue<Object> workQueue;
    // 子任务处理结果集
    protected Map<String, Object> resultMap;

    public void setWorkQueue(Queue<Object> workQueue){
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, Object> resultMap){
        this.resultMap = resultMap;
    }

    // 子任务的逻辑处理，在子类中实现具体逻辑
    protected Object handle(Object input){
        return input;
    }

    @Override
    public void run() {
        while (true){
            // 获取子任务
            Object input = workQueue.poll();
            if (input == null)
                break;
            // 处理子任务
            Object re = handle(input);
            // 将处理结果写入结果集
            resultMap.put(Integer.toString(input.hashCode()), re);
        }
    }
}
