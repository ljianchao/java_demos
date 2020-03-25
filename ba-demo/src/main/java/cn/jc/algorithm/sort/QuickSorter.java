package cn.jc.algorithm.sort;

/**
 * 交换排序-快速排序
 *
 */
public class QuickSorter implements Sorter<Integer> {


    public static void main(String[] args) {

    }

    @Override
    public Integer[] sort(Integer[] array) {
        if (array == null)
            throw new NullPointerException("数据对象为null");
        return quickSort(array, 0, array.length);
    }


    /**
     * 快速排序又称为划分交换排序
     *
     * @param array
     * @return
     */
    public Integer[] quickSort(Integer[] array, int start, int end) {

        return null;
    }

    public int partition(Integer array[], int start, int end) {
        // 用数组内第一个元素作为基准
        int pivot = array[0];

        while (start < end)  {


        }

        return 0;
    }
}
