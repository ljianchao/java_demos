package cn.jc;

import cn.jc.util.ArrayList;
import cn.jc.util.List;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    // 测试新建map
    private static Map<String, Integer> keytype = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 6207897507346633280L;
        private int index = 1;
        {
            put("int", index++);
            put("string", index++);
            put("long", index++);
            put("byte", index++);
            put("short", index++);
        }
    };

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
//        ArrayList<User> oriList = new ArrayList<>();
//        oriList.add(new User("L", 1));
//        oriList.add(new User("J", 2));
//        ArrayList<User> cloneList = (ArrayList)oriList.clone();
//
//        System.out.println("original list:");
//        for (User user:
//             oriList) {
//            System.out.println("name=" + user.getName() + "; age=" + user.getAge());
//        }
//        cloneList.get(1).setName("X");
//
//        System.out.println("original list:");
//        for (User user:
//                oriList) {
//            System.out.println("name=" + user.getName() + "; age=" + user.getAge());
//        }
//
//        System.out.println("destination list:");
//        for (User user:
//                oriList) {
//            System.out.println("name=" + user.getName() + "; age=" + user.getAge());
//        }


//        for (Map.Entry<String, Integer> entry:
//             App.keytype.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }

        // App.testArrayAssign();
        App.testRemove();
    }

    public static void testArrayAssign(){
        Object[] a = {1, 2, 3};
        final Object[] b = a;
        a[0] = 4;
        System.out.println("array a :");
        for (int i = 0; i < a.length; i++)
            System.out.println("index = " + i + ", Value = " + a[i]);

        System.out.println("array b :");
        for (int i = 0; i < b.length; i++)
            System.out.println("index = " + i + ", Value = " + b[i]);

        System.out.println(a.equals(b));
    }

    public static void testRemove(){
        List<String> origList = new ArrayList<String>(){
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
                add("f");
            }
        };

        List<String> removeList = new ArrayList<String>(){
            {
                add("b");
                add("d");
                add("f");
            }
        };

        origList.removeAll(removeList);

        for (String str: origList)
            System.out.println("value = " + str);
    }
}
