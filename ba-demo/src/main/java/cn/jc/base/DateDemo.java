package cn.jc.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 有关日期的demo
 *
 */
public class DateDemo {

    static void printYesterdayCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        System.out.println(calendar.getTime());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTimeInMillis()));
    }

    public static void main(String[] args) {
        System.out.println("输入昨天的当前时刻");
        printYesterdayCurrent();
    }
}
