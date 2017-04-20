package cn.jc.demos.concurrency.future;

import cn.jc.demos.concurrency.future.Data;

/**
 * Created by Administrator on 2016/6/5.
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String para) {
        StringBuffer sb = new StringBuffer();
        sb.append(para);
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){

        }
        result = sb.toString();
    }

    public String getResult() {
        return result;
    }
}
