package cn.jc.design.pipe;

import java.util.concurrent.TimeUnit;

/**
 * Created by muzhiye on 2019/3/19.
 */
public interface Pipe<IN, OUT> {

    void setNextPipe(Pipe<?, ?> nextPipe);

    void process(IN input) throws InterruptedException;

    void init(PipeContext pipeCtx);

    void shutdown(long timeout, TimeUnit unit);
}
