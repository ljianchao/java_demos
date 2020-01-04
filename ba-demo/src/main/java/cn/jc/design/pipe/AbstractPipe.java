package cn.jc.design.pipe;

import java.util.concurrent.TimeUnit;

/**
 * Created by muzhiye on 2019/3/19.
 */
public abstract class AbstractPipe<IN, OUT> implements Pipe<IN, OUT> {

    protected volatile Pipe<?, ?> nextPipe;
    protected volatile PipeContext pipeCxt;

    @Override
    public void setNextPipe(Pipe<?, ?> nextPipe) {
        this.nextPipe = nextPipe;
    }


    @Override
    public void process(IN input) throws InterruptedException {
        try {
            OUT out = doProcess(input);
            if (nextPipe != null && out != null) {
                ((Pipe<OUT, ?>)nextPipe).process(out);
            }
        } catch (PipeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(PipeContext pipeCtx) {
        this.pipeCxt = pipeCtx;
    }

    @Override
    public void shutdown(long timeout, TimeUnit unit) {

    }

    protected abstract OUT doProcess(IN input) throws PipeException;
}
