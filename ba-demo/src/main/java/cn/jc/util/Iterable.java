package cn.jc.util;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/3/31.
 */
public interface Iterable<T> {

    Iterator<T> iterator();

}
