package cn.jc.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * abstract类
 * 不实现interface的所有接口也不会报错
 */
public abstract class AbstractCollection<E> implements Collection<E>{

    // abstract类构造函数
    // protected只允许子类进行访问
    protected AbstractCollection(){

    }

    // abstract方法实现interface的方法
    // 无需实现体
    public abstract java.util.Iterator<E> iterator();

    public abstract int size();

    public boolean isEmpty(){
        return size() == 0;
    }

    // 进行迭代实现
    public boolean contains(Object o){
        java.util.Iterator<E> it = iterator();
        if(o == null){
            while (it.hasNext())
                if(it.next() == null)
                    return  true;
        } else {
            while (it.hasNext())
                // 对象进行hash比较
                if (o.equals(it.next()))
                    return true;
        }
        return false;
    }

    public Object[] toArray(){
        Object[] r = new Object[size()];
        java.util.Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++){
            // 元素比期望的少
            if(!it.hasNext())
                return Arrays.copyOf(r, i);

            r[i] = it.next();
        }
        // 检验是否有新增项
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     *
     * @param a 数组a最好是内容都为null的数组
     * @param <T>
     * @return
     */
    public <T> T[] toArray(T[] a){
        int size = size();
        // a数组或创建要返回的新数组对象
        T[] r = a.length >= size ? a :
                (T[])java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size);
        java.util.Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++){
            // 元素个数少于预期
            if(!it.hasNext()) {
                if (a == r) {
                    // 将剩余空间的的元素设置为null
                    r[i] = null;
                } else if (a.length < i) {
                    // collection元素被删除，无法填充满新数组r
                    // i大于数组a的length
                    // 顾截断新数组的长度
                    return Arrays.copyOf(r, i);
                } else {
                    // collection元素被删除，无法填充满新数组r
                    // i小于等于数组a的length
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }

                return a;
            }
            r[i] = (T) it.next();
        }

        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * 分配数组的最大空间
     * 一些VM保留了一些头信息在数组中( -8 )
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 当iterator对象返回的元素比预期的更多时，
     * 重新分配toArray方法处理的数组，
     * 并从iterator对象获取数据填充该数字
     * @param r
     * @param it
     * @param <T>
     * @return
     */
    private static <T> T[] finishToArray(T[] r, java.util.Iterator<?> it){
        int i = r.length;
        while (it.hasNext()){
            int cap = r.length;
            // 数组的容量不够，需重新创建新的更大容量的数组
            if(i == cap){
                // TODO:该算法怎么理解？
                // 此算法的结果总是偶数
                int newCap = cap + (cap >> 1) + 1;
                // 检查是否溢出
                if(newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                // 创建新数组
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T)it.next();
        }

        // 截断溢出的元素
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    /**
     * 计算数组的最大容量
     * @param miniCapacity
     * @return
     */
    private static int hugeCapacity(int miniCapacity){
        if(miniCapacity < 0)
            throw new OutOfMemoryError("Required array size too large");

        return (miniCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    public boolean add(E e){
        throw new UnsupportedOperationException();
    }

    // 依赖iterator.remove()
    // collection必须实现iterator对象的remove方法
    public boolean remove(Object o){
        java.util.Iterator<E> it = iterator();
        if(o == null){
            while (it.hasNext()){
                if(it.next() == null){
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()){
                if(o.equals(it.next())){
                    it.remove();
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsAll(Collection<?> c){
        for(Object e: c)
            if(!contains(e))
                return false;

        return true;
    }

    // 依赖add()方法
    // collection必须overridden add()方法
    // 只要成功添加成功1个对象，就会返回true
    public boolean addAll(Collection<? extends E> c){
        boolean modified = false;
        for(E e: c)
            if(add(e))
                modified = true;
        return modified;
    }

    // 依赖iterator.remove()
    // collection必须实现iterator对象的remove方法
    // 只要成功删除1个对象，就会返回true
    public boolean removeAll(Collection<?> c){
        Objects.requireNonNull(c);
        boolean modified = false;
        java.util.Iterator<?> it = iterator();
        while (it.hasNext()){
            if(c.contains(it.next())){
                it.remove();
                modified = true;
            }
        }

        return modified;
    }

    // 依赖iterator.remove()
    // collection必须实现iterator对象的remove方法
    // 只要成功删除1个对象，就会返回true
    public boolean retainAll(Collection<?> c){
        Objects.requireNonNull(c);
        boolean modified = false;
        java.util.Iterator<E> it = iterator();
        while (it.hasNext()){
            if(!c.contains(it.next())){
                it.remove();
                modified = true;
            }
        }

        return modified;
    }

    // 依赖iterator.remove()
    // collection必须实现iterator对象的remove方法
    // 大多数实现者都会选择override此方法
    public void clear(){
        java.util.Iterator<E> it = iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
    }
}
