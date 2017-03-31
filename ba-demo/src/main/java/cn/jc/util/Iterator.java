package cn.jc.util;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/3/31.
 */
public interface Iterator<E> {

    boolean hasNext();

    E next();

    default void remove(){
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(Consumer<? super E> action){
        // TODO:
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
