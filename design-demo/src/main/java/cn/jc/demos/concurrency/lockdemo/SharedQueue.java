package cn.jc.demos.concurrency.lockdemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2016/6/7.
 */
public class SharedQueue {
    public static final BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<String>();
}
