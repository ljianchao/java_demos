package cn.jc.generics;

/**
 * Created by muzhiye on 2019/1/31.
 */
public class Node<T> {

    public T data;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}
