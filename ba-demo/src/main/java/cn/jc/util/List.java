package cn.jc.util;

import java.util.*;

/**
 * Created by Administrator on 2017/4/7.
 * @author Jack Liu
 */
public interface List<E> extends Collection<E> {
    // 查询操作
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    java.util.Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    // 更新操作
    boolean add(E e);

    boolean remove(Object o);

    // 批量操作
    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends E> c);

    /**
     * 指定位置插入（可选操作）
     * @param index
     * @param c
     * @return
     */
    boolean addAll(int index, Collection<? extends E> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

    // 比较和hash

    // 明确需要override
    boolean equals(Object o);

    int hashCode();

    // 定位访问操作
    /**
     * 获取指定索引出的元素
     * @param index
     * @return
     */
    E get(int index);

    /**
     *
     * @param index
     * @param element
     * @return 以前在指定位置的元素
     */
    E set(int index, E element);

    /**
     *
     * @param index
     * @param element
     */
    void add(int index, E element);

    E remove(int index);

    // 搜索操作
    int indexOf(Object o);

    int lastIndexOf(Object o);

    // List 迭代器
    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int index);

    // view
    List<E> subList(int fromIndex, int toIndex);
}
