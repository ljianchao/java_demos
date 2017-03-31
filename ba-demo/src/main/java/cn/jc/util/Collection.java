package cn.jc.util;

/**
 * Created by Jc on 2017/3/30.
 * 必须实现java.lang.Iterable接口才能进行foreach
 */
public interface Collection<E> extends java.lang.Iterable<E> {

    // 查询操作
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    java.util.Iterator<E> iterator();

    // 返回Object类型数组
    Object[] toArray();

    // 返回泛型类型数组
    <T> T[] toArray(T[] a);

    // 更新操作
    // 必须指定泛型类型E
    boolean add(E e);

    // 类型为Object
    boolean remove(Object o);

    // 批量操作
    // 泛型"?"通配符表示任意类型
    boolean containsAll(Collection<?> c);

    // <? extends E>表示E类型或E的子类型
    boolean addAll(Collection<? extends E> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

    // 比较和hash
    boolean equals(Object c);

    int hashCode();
}
