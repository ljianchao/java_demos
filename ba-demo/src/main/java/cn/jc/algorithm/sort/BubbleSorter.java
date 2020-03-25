package cn.jc.algorithm.sort;

import java.util.Arrays;

/**
 * 交换排序-冒泡排序
 *
 */
public class BubbleSorter implements Sorter<Integer> {

    public static void main(String[] args) {
//        int[] array = {43, 23, 21};
        Integer[] array = {23, 43, 50, 21};
        System.out.println("待排序数组为：" + Arrays.toString(array));

        Sorter<Integer> sorter = new BubbleSorter();
        array = sorter.sort(array);

        System.out.println("冒泡排序结果为：" + Arrays.toString(array));
    }

    /**
     * 冒泡排序，结果为升序
     * 通过相邻元素之间的比较和交换，使关键字较小的元素逐渐从底部移向顶部，
     * 就像水底的气泡一样逐渐向上冒泡（每趟只冒出一个元素）
     *
     * "稳定的"排序
     * 平均时间复杂度为O(n^2)，最好情况（数组本身为正序）为O(n)，
     * 最坏情况（数组本身为逆序）为O(n^2)，空间复杂度为O(1)
     *
     * @param array
     * @return
     */
    public Integer[] bubbleSort(Integer[] array) {
        if (array == null)
            throw new NullPointerException("数组为null");
        if (array.length == 0)
            return array;

        int i, j, temp;
        // 表示每一趟是否有交换的标识
        int flag;
        for (i = 0; i < array.length - 1; i++) {    // 最多进行n-1趟排序
            // 每趟开始前先置0
            flag = 0;
            for (j = array.length - 1; j > i; j--) {    // 采用自底向上扫描数组做冒泡排序
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    flag = 1;
                }
            }

//            System.out.println(Arrays.toString(array));

            // 如果某一趟中没有交换，说明数组已完成了排序
            if (flag == 0)
                return array;
        }

        return array;
    }

    @Override
    public Integer[] sort(Integer[] array) {
        return bubbleSort(array);
    }
}
