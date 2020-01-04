package cn.jc.generics;

import org.springframework.context.annotation.Scope;

/**
 * Created by muzhiye on 2019/1/31.
 */
public class MyNode extends Node<Integer> {

    public MyNode(Integer data) {
        super(data);
    }

    @Scope
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }

}
