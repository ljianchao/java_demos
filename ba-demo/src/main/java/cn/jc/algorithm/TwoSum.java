package cn.jc.algorithm;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    public static void main( String[] args ) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        int[] indices = twoSum(nums, target);

        System.out.println("暴力方法匹配的索引为：" + Arrays.toString(indices));

        indices = twoSumUseTwiceHash(nums, target);

        System.out.println("两遍哈希表方法匹配的索引为：" + Arrays.toString(indices));

        indices = twoSumUseOnceHash(nums, target);

        System.out.println("一遍哈希表方法匹配的索引为：" + Arrays.toString(indices));

        indices = twoSumUseBinarySearch(nums, target);

        System.out.println("二分查找方法匹配的索引为：" + Arrays.toString(indices));
    }

    /**
     * 暴力法很简单，遍历每个元素 x，并查找是否存在一个值与 target - x 相等的目标元素。
     *
     * 复杂度分析：
     *
     * 时间复杂度：O(n^2)
     * 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n)O(n) 的时间。因此时间复杂度为 O(n^2)。
     *
     * 空间复杂度：O(1)。
     *
     * 数据规模很大时，无法使用
     * @param nums
     * @param target
     * @return
     */
    static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
//                System.out.println(nums[i] + ", " + nums[j]);
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }

    /**
     * 采用两遍哈希表
     * 通过以空间换取速度的方式，我们可以将查找时间从 O(n) 降低到 O(1)。
     * 哈希表正是为此目的而构建的，它支持以 近似 恒定的时间进行快速查找。
     * 用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。
     * 但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)。
     *
     * 复杂度分析：
     *
     * 时间复杂度：O(n)
     *
     * 空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    static int[] twoSumUseTwiceHash(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] {i, map.get(complement)};
            }
        }

        return null;
    }

    /**
     * 采用一遍哈希表
     *
     * 复杂度分析：
     *
     * 时间复杂度：O(n)
     *
     * 空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    static int[] twoSumUseOnceHash(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return null;
    }

    /**
     * 时间复杂度：O(NlogN)
     * @param nums
     * @param target
     * @return
     */
    static int[] twoSumUseBinarySearch(int[] nums, int target) {

        int[] original = Arrays.copyOf(nums, nums.length);

        // 该函数时间复杂度为O(NlogN)
        Arrays.sort(nums);

        int num1 = -1;
        int num2 = -1;
        int start = 0;
        int end = nums.length - 1;
        // 该循环的时间复杂度为O(logN)
        while (start != end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                num1 = nums[start];
                num2 = nums[end];
                break;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }

        if (num1 == -1 && num2 == -1) {
            return null;
        }

        int[] rst = new int[2];
        rst[0] = -1;
        rst[0] = -1;
        for (int i = 0; i < original.length; i++) {
            if (original[i] == num1 || original[i] == num2) {
                if (rst[0] == -1) {
                    rst[0] = i;
                } else {
                    rst[1] = i;
                    break;
                }
            }
        }

        if (rst[0] == -1)
            return null;

        return rst;
    }
}
