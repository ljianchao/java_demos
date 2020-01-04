package cn.jc.design.pipe;

/**
 * Created by muzhiye on 2019/3/19.
 */
public class PipeException extends Exception{

    private final Pipe<?, ?> sourcePipe;

    private final Object input;

    public PipeException(Pipe<?, ?> sourcePipe, Object input, String message) {
        super(message);
        this.sourcePipe = sourcePipe;
        this.input = input;
    }

    public PipeException(Pipe<?, ?> sourcePipe, Object input, String message, Throwable cause) {
        super(message, cause);
        this.sourcePipe = sourcePipe;
        this.input = input;
    }

    public Pipe<?, ?> getSourcePipe() {
        return sourcePipe;
    }

    public Object getInput() {
        return input;
    }
}
