package cn.jc.util;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2017/4/7.
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    protected AbstractList(){

    }

    /**
     * list末尾添加元素
     * @param e
     * @return
     */
    public boolean add(E e){
        add(size(), e);
        return true;
    }

    public abstract E get(int index);

    public E set(int index, E element){
        throw new UnsupportedOperationException();
    }

    public void add(int index, E element){
        throw new UnsupportedOperationException();
    }

    public E remove(int index){
        throw new UnsupportedOperationException();
    }

    // search 操作
    public int indexOf(Object o){
        ListIterator<E> it = listIterator();
        if(o == null){
            while (it.hasNext()){
                if(it.next() == null)
                    return it.previousIndex();
            }
        } else {
            while (it.hasNext()){
                if (o.equals(it.next()))
                    return it.previousIndex();
            }
        }

        return -1;
    }

    public int lastIndexOf(Object o){
        ListIterator<E> it = listIterator(size());
        if (o == null){
            while (it.hasPrevious()){
                if (it.previous() == null)
                    return it.nextIndex();
            }
        } else {
            while (it.hasPrevious()){
                if (o.equals(it.previous()))
                    return it.nextIndex();
            }
        }
        return -1;
    }

    public void clear(){
        removeRange(0, size());
    }

    public boolean addAll(int index, Collection<? extends E> c){
        rangeCheckForAdd(index);
        boolean modified = false;
        for(E e: c){
            add(index++, e);
            modified = true;
        }

        return modified;
    }

    public java.util.Iterator<E> iterator(){
        return new Itr();
    }

    public ListIterator<E> listIterator(){
        return listIterator(0);
    }

    /**
     *
     * @param index final类型定义index值在方法内部不允许修改
     * @return
     */
    public ListIterator<E> listIterator(final int index){
        rangeCheckForAdd(index);

        return new ListItr(index);
    }

    /**
     * private类型
     */
    private class Itr implements java.util.Iterator<E> {

        // 下一个需要被访问的元素索引
        int cursor = 0;
        // 最近被访问的元素索引
        // 如果调用remove方法删除当前元素，重置为-1
        int lastRet = -1;

        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public E next() {
            checkForComodification();
            try {
                int i = cursor;
                E next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e){
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove(){
            if(lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();
            try {
                // 调用外部类对象实例的remove方法
                AbstractList.this.remove(lastRet);
                // 最后访问的元素索引小于指针
                if (lastRet < cursor)
                    cursor--;


                lastRet = -1;
                //TODO：重新赋值逻辑？
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        // final方法不能被继承
        final void checkForComodification(){
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * 父类有接口相应的实现方法，子类实现该接口时可以不实现相应的方法
     */
    private class ListItr extends Itr implements ListIterator<E>{

        ListItr(int index){
            // 重置cursor
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public E previous() {
            checkForComodification();
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e){
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                AbstractList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }

        }
    }

    public List<E> subList(int fromIndex, int toIndex){
        return null;
    }

    public boolean equals(Object o){
        return true;
    }

    public int hashCode(){
        return -1;
    }

    /**
     * 被本list和subLists的clear方法调用
     * @param fromIndex
     * @param toIndex
     */
    protected void removeRange(int fromIndex, int toIndex){
        ListIterator<E> it = listIterator(fromIndex);
        for (int i = 0, n = toIndex - fromIndex; i < n; i++){
            it.next();
            it.remove();
        }
    }

    // transient临时变量，不能被序列化
    protected transient int modCount = 0;

    private void rangeCheckForAdd(int index){
        //TODO:为什么不考虑大于等于的情况?
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundMsg(index));
    }

    private String outOfBoundMsg(int index){
        return "Index: " + index + ", Size: " + size();
    }
}
