package cn.jc;

import cn.jc.design.proxy.GamePlayIH;
import cn.jc.design.proxy.GamePlayer;
import cn.jc.design.proxy.GamePlayerImpl;
import cn.jc.util.ArrayList;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        GamePlayer player = new GamePlayerImpl("张三");
//
//        // 定义一个handler
//        InvocationHandler handler = new GamePlayIH(player);
//
//        ClassLoader classLoader = player.getClass().getClassLoader();
//        GamePlayer proxy = (GamePlayer) Proxy.newProxyInstance(classLoader,new Class[]{GamePlayer.class}, handler);
//        proxy.login("zhangsan", "pwd");
//        proxy.killBoss();
//        proxy.upgrade();

        // 测试ArrayList
//        cn.jc.util.List<String> strList = new cn.jc.util.ArrayList<>();
//        strList.add("Jack");
//        strList.add("Liu");
//        cn.jc.util.List<String> strListClone = new cn.jc.util.ArrayList<>(strList);
//        for (String str :
//                strListClone) {
//            System.out.println(str);
//        }
//
//        cn.jc.util.ArrayList<String> defaultList = new cn.jc.util.ArrayList<>();
//        defaultList.add("1");
//        defaultList.ensureCapacity(11);

//        String[] as = {"1","2", "3"};
//        int index = 0;
//        System.out.println(as[index++]);
//        System.out.println(as[index++]);
//        System.out.println(as[index++]);

        // 测试ArrayList的浅表副本clone()
        ArrayList<User> oriList = new ArrayList<>();
        oriList.add(new User("L", 1));
        oriList.add(new User("J", 2));
        ArrayList<User> cloneList = (ArrayList)oriList.clone();

        System.out.println("original list:");
        for (User user:
             oriList) {
            System.out.println("name=" + user.getName() + "; age=" + user.getAge());
        }
        cloneList.get(1).setName("X");

        System.out.println("original list:");
        for (User user:
                oriList) {
            System.out.println("name=" + user.getName() + "; age=" + user.getAge());
        }

        System.out.println("destination list:");
        for (User user:
                oriList) {
            System.out.println("name=" + user.getName() + "; age=" + user.getAge());
        }

    }
}
