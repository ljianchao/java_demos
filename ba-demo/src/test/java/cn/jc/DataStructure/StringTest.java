package cn.jc.DataStructure;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
public class StringTest {

    /**
     * 测试String的常量池优化
     */
    @Test
    public void testStringConstPool(){
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        // str1和str2的引用相同
        System.out.println(str1 == str2);
        // str1和str3的引用不相同
        System.out.println(str1 == str3);
        // str1、str2和str3在常量池中的引用一样
        System.out.println(str1 == str3.intern());
    }

}
