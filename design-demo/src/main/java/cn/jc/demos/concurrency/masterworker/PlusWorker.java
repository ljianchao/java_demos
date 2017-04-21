package cn.jc.demos.concurrency.masterworker;

/**
 * Created by Administrator on 2017/4/21.
 */
public class PlusWorker extends Worker {

    // worker 求立方和
    public Object handle(Object input){
        Integer i = (Integer) input;
        return i*i*i;
    }
}
