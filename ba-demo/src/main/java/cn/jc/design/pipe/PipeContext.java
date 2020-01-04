package cn.jc.design.pipe;

/**
 * Created by muzhiye on 2019/3/19.
 */
public interface PipeContext {

    void handleError(PipeException exception);
}
