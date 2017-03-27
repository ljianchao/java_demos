package cn.jc;

import cn.jc.design.proxy.GamePlayIH;
import cn.jc.design.proxy.GamePlayer;
import cn.jc.design.proxy.GamePlayerImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        GamePlayer player = new GamePlayerImpl("张三");

        // 定义一个handler
        InvocationHandler handler = new GamePlayIH(player);

        ClassLoader classLoader = player.getClass().getClassLoader();
        GamePlayer proxy = (GamePlayer) Proxy.newProxyInstance(classLoader,new Class[]{GamePlayer.class}, handler);
        proxy.login("zhangsan", "pwd");
        proxy.killBoss();
        proxy.upgrade();
    }
}
