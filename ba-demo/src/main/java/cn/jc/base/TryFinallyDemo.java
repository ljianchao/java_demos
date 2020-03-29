package cn.jc.base;

import java.util.HashMap;
import java.util.Map;

public class TryFinallyDemo {

    public static void main(String[] args) {
        System.out.println("try中return结果：" + test1());
        System.out.println("finally中return结果：" + test2());
        System.out.println("finally中更新值结果：" + test3());
        System.out.println("finally中更新map的值结果：" + getMap().get("KEY"));
    }

    /**
     * finally语句在return语句执行之后return返回之前执行的。
     * @return
     */
    public static int test1() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");

            if (b > 25) {
                System.out.println("b > 25, b = " + b);
            }
        }

        return b;
    }

    /**
     * finally块中的return语句会覆盖try块中的return返回
     * @return
     */
    public static int test2() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");

            if (b > 25) {
                System.out.println("b > 25, b = " + b);
            }

            return 200;
        }
    }

    /**
     * 如果finally语句中没有return语句覆盖返回值，那么原来的返回值可能因为finally里的修改而改变也可能不变。
     *
     * @return
     */
    public static int test3() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");

            if (b > 25) {
                System.out.println("b > 25, b = " + b);
            }

            b = 150;
        }

        return 195;
    }

    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();

        try {
            map.put("KEY", "TRY");

            return map;

        } catch (Exception e) {
            map.put("KEY", "CATCH");
        } finally {
            map.put("KEY", "FINALLY");

            // 对引用的改变不生效
            map = null;
        }

        return map;
    }
}
