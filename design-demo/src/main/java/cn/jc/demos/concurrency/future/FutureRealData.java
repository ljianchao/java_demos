package cn.jc.demos.concurrency.future;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/4/20.
 */
public class FutureRealData implements Callable<String> {
    private String para;

    public FutureRealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(para);

        Thread.sleep(5000L);

        return sb.toString();
    }
}
