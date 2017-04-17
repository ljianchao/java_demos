package cn.jc.util;

import java.util.Arrays;
import java.util.RandomAccess;

/**
 * Created by Administrator on 2017/4/13.
 */
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable,java.io.Serializable
{
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    // non-private to simplify nested class access
    transient Object[] elementData;

    private int size;

    public ArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(int initialCapacity){
        if (initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        } else if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                                                initialCapacity);
        }
    }

    public ArrayList(Collection<? extends E> c){
        elementData = c.toArray();
        if ((size = elementData.length) != 0){
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    public void trimToSize(){
        // TODO:为什么modCount要更新？
        modCount++;
        if (size < elementData.length){
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity){
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                ? 0
                : DEFAULT_CAPACITY;
        if (minCapacity > minExpand){
            ensureExplicitCapacity(minCapacity);
        }
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void ensureCapacityInternal(int minCapacity){
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity){
        // add方法必须处理的
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity){
        // overflow-conscious code
        int oldCapacity = elementData.length;
        // 容量增加
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // 复制指定的数组
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity){
        if (minCapacity > 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object o){
        return indexOf(o) > 0;
    }

    public int indexOf(Object o){
        if (o == null){
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }

        return -1;
    }

    public int lastIndexOf(Object o){
        if (o == null){
            for (int i = size - 1; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    // 浅表副本
    // 元素是引用类型，复制的只是对象的引用
    public Object clone(){
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            // elementData是protect访问权限
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    E elementData(int index){
        return (E) elementData[index];
    }

    public Object[] toArray(){
        return Arrays.copyOf(elementData, size);
    }

    public <T> T[] toArray(T[] a){
        if (a.length < size)
            return (T[])Arrays.copyOf(elementData, size, a.getClass());

        System.arraycopy(elementData, 0, a, 0, size);
        // 将数组中紧接collection尾部的元素设置为null
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    public E set(int index, E element){
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    public boolean add(E e){
        ensureCapacityInternal(size + 1);   // increments modCount!!
        elementData[size++] = e;
        return true;
    }

    public void add(int index, E element){
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);  // elemantData数组进行数组移动
        elementData[index] = element;
        size++;
    }

    public E remove(int index){
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    public boolean remove(Object o){
        if (o == null){
            for (int index = 0; index < size; index++)
                if (elementData[index] == null){
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])){
                    fastRemove(index);
                    return true;
                }
        }

        return false;
    }

    private void fastRemove(int index){
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                        numMoved);
        elementData[--size] = null;
    }

    public void clear(){
        modCount++;

        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    public boolean addAll(Collection<? extends E> c){
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    public boolean addAll(int index, Collection<? extends E> c){
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);  //先移动原数组
        System.arraycopy(a, 0, elementData, index, numNew);   // 添加新数组元素
        size += numNew;
        return numNew != 0;
    }

    private void rangeCheck(int index){
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index){
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
