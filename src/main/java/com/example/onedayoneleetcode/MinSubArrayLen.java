package com.example.onedayoneleetcode;

/**
 * 题目：一个数组，一个target，输出最短的大于等于target的连续子数组和，没有输出0。
 *
 *
 * 你可以使用滑动窗口（Sliding Window）算法来解决这个问题。滑动窗口算法用于在一个数组或序列中寻找满足特定条件的子数组或子序列。
 * 在这个问题中，我们要找到最短的大于等于target的连续子数组和。
 *
 *
 * 这段代码中，我们使用了两个指针 left 和 right 来构建一个滑动窗口。开始时，窗口为空，left 和 right 都指向数组的第一个元素。
 * 我们不断移动右边界 right，并将元素加入窗口的和 sum 中，直到 sum 大于等于 target。然后，我们尝试缩小窗口的左边界 left，
 * 直到 sum 小于 target。在这个过程中，我们不断更新最短子数组的长度 minLength。最后，返回 minLength 的值，
 * 如果它仍然是初始值（无穷大），则说明没有找到满足条件的子数组，返回0。
 *
 *
 * 这个算法的时间复杂度为O(N)，其中N是数组的长度，因为我们最多遍历一次数组。这个算法在空间上是O(1)，因为它只使用了常数级别的额外空间
 */
public class MinSubArrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;    // 滑动窗口的左边界
        int sum = 0;     // 滑动窗口中的元素和
        int minLength = Integer.MAX_VALUE; // 最短子数组长度，默认为无穷大

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // 将右边界元素加入滑动窗口

            // 当滑动窗口的和大于等于target时，尝试缩小窗口左边界
            while (sum >= target) {
                // 更新最短长度
                minLength = Math.min(minLength, right - left + 1);

                // 缩小窗口左边界
                sum -= nums[left];
                // 移动左边界
                left++;
            }
        }

        // 如果minLength仍然是初始值，说明没有找到满足条件的子数组
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

}
