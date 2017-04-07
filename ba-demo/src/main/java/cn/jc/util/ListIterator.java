package cn.jc.util;

/**
 * Created by Administrator on 2017/4/7.
 */
public interface ListIterator<E> extends java.util.Iterator<E>{
    // 查询操作
    boolean hasNext();

    E next();

    /**
     *
     * @return
     */
    boolean hasPrevious();

    E previous();

    int nextIndex();

    int previousIndex();

    // 更新操作
    void remove();

    void set(E e);

    void add(E e);
}
