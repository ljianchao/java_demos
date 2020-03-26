package cn.jc.base;

/**
 * 展示int和Integer的区别
 * 1，无论如何，Integer与new Integer不会相等。不会经历拆箱过程，new出来的对象存放在堆，而非new的Integer常量则在常量池（在方法区），他们的内存地址不一样，所以为false。
 *
 * 2，两个都是非new出来的Integer，如果数在-128到127之间，则是true,否则为false。因为java在编译Integer i2 = 128的时候,被翻译成：Integer i2 = Integer.valueOf(128);而valueOf()函数会对-128到127之间的数进行缓存。
 *
 * 3，两个都是new出来的,都为false。还是内存地址不一样。
 *
 * 4，int和Integer(无论new否)比，都为true，因为会把Integer自动拆箱为int再去比。
 *
 */
public class IntegerDemo {

    public static void main(String[] args) {
        int i = 127;
        Integer i2 = 127;
        Integer i3 = new Integer(127);
        System.out.println("(int i = 127)和(Integer i2 = 127)相等比较结果为：" + Boolean.toString(i == i2)); // Integer会自动拆箱为int，结果为true
        System.out.println("(int i = 127)和(Integer i3 = new Integer(127))相等比较结果为：" + Boolean.toString(i == i3));// Integer会自动拆箱为int，结果为true
        System.out.println("(Integer i2 = 127)和(Integer i3 = new Integer(127))相等比较结果为：" + Boolean.toString(i2 == i3));

        Integer i4 = 127;   // 编译为：Integer i4 = Integer.valueOf(127);
        Integer i5 = 127;
        // 结果为true，Integer.valueOf()函数会对-128到127之间的数进行缓存
        System.out.println("(Integer i4 = 127)和(Integer i5 = 127)相等比较结果为" + Boolean.toString(i4 == i5));

        Integer i6 = 128;
        Integer i7 = 128;
        // 结果为false，两个对象的内存地址不一样
        System.out.println("(Integer i6 = 128)和(Integer i7 = 128)相等比较结果为" + Boolean.toString(i6 == i7));

        Integer i8 = new Integer(128);
        Integer i9 = new Integer(128);
        System.out.println("(Integer i8 = new Integer(128))和(Integer i9 = new Integer(128))相等比较结果为" + Boolean.toString(i8 == i9));
    }
}
