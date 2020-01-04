package cn.jc.design.pipe;

/**
 * Created by muzhiye on 2019/3/19.
 */
public interface PipeLine<IN, OUT> extends Pipe<IN, OUT> {

    void addPipe(Pipe<?, ?> pipe);
}
