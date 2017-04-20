package cn.jc.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/24.
 */
public class GamePlayIH  implements InvocationHandler{

    Class cls = null;

    Object obj = null;

    public GamePlayIH(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.obj, args);
        return result;
    }
}
