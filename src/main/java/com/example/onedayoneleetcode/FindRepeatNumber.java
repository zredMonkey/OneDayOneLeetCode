package com.example.onedayoneleetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Description:
 *  题目：
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 * 限制：
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 *
 */
public class FindRepeatNumber {
    
    /**
     * 使用集合set:
     *
     * 由于只需要找出数组中任意一个重复的数字，因此遍历数组，遇到重复的数字即返回。
     * 为了判断一个数字是否重复遇到，使用集合存储已经遇到的数字，
     * 如果遇到的一个数字已经在集合中，则当前的数字是重复数字。
     *
     * 初始化集合为空集合，重复的数字 repeat = -1
     * 遍历数组中的每个元素：
     *    将该元素加入集合中，判断是否添加成功
     *    如果添加失败，说明该元素已经在集合中，因此该元素是重复元素，将该元素的值赋给 repeat，并结束遍历
     * 返回 repeat
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/mian-shi-ti-03-shu-zu-zhong-zhong-fu-de-shu-zi-b-4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 复杂性分析:
     * 时间复杂度：O(n)O(n)。
     * 遍历数组一遍。使用哈希集合（HashSet），添加元素的时间复杂度为 O(1)O(1)，故总的时间复杂度是 O(n)O(n)。
     * 空间复杂度：O(n)O(n)。
     * 不重复的每个元素都可能存入集合，因此占用 O(n)O(n) 额外空间。
     *
     **/
    public static int findRepeatNumber(int[] nums) {
        // 创建集合set
        Set<Integer> set = new HashSet<Integer>();
        // 重复数字
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }

    /** 
     * 使用临时数组
     *
     * 这道题有个很明显的特点，就是数字的大小在0~n-1之间，所以使用上面两种方式肯定不是最好的选择。
     * 这里我们可以申请一个临时数组temp，因为nums元素中的每个元素的大小都在0~n-1之间，
     * 所以我们可以把nums中元素的值和临时数组temp建立映射关系，就是nums中元素的值是几，
     * 我们就把temp中对应的位置值加1，当temp某个位置的值大于1的时候，就表示出现了重复，我们直接返回即可
     *
     **/
    public static int findRepeatNumber2(int[] nums) {
        int length = nums.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            // nums中元素的值是几，把temp中对应的位置值加1
            temp[nums[i]]++;
            if (temp[nums[i]] > 1){
                return nums[i];
            }
        }
        return -1;
    }

    /** 
     * 放到指定的位置:
     *
     * 我们还可以不使用临时数组，我们在遍历的时候把数组nums中的值放到对应的位置上，
     * 比如某个元素是5，我们就把他放到nums[5]中，每次放入的时候查看一下这个位置是否放入了正确的值，
     * 如果已经放入了正确的值，就说明重复了，我们直接返回即可。
     *
     **/
    public static int findRepeatNumber3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //位置正确，先不用管
            if (i == nums[i]) {
                continue;
            }
            //出现了重复，直接返回
            if (nums[i] == nums[nums[i]]) {
                return nums[i];
            }
            //交换
            int temp = nums[nums[i]];
            nums[nums[i]] = nums[i];
            nums[i] = temp;
            // 这里的i--是为了抵消掉上面的i++，
            // 交换之后需要原地再比较
            // 因为每次比较会把num[0]的元素放到正确的位置上。所以交换之后需要再比较一次
            i--;
        }
        return -1;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int result = findRepeatNumber3(nums);
        System.out.println("结果是：" + result);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + (endTime - startTime) + " ms");
    }
}
