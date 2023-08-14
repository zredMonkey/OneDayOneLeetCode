package com.example.onedayoneleetcode;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 */

public class LongestIncreasingSubsequence {

    /**
     * 1. 我们使用一个数组 `dp` 来保存以每个元素结尾的最长递增子序列的长度。初始时，每个元素自成一个长度为1的子序列。
     * 2. 我们从数组的第二个元素开始遍历，对于每个元素 `nums[i]`，我们再遍历它之前的所有元素 `nums[j]`（`j < i`）。如果 `nums[i]` 大于 `nums[j]`，说明可以将 `nums[i]` 加入以 `nums[j]` 结尾的子序列，从而构成一个更长的递增子序列。我们更新 `dp[i]` 为 `dp[j] + 1`，表示以 `nums[i]` 结尾的最长递增子序列长度。
     * 3. 在内层循环中，我们不断更新 `dp[i]`，找到以当前元素 `nums[i]` 结尾的最长递增子序列长度。
     * 4. 在整个过程中，我们维护一个全局变量 `maxLen`，记录最长递增子序列的长度。
     * 5. 最终，遍历完整个数组后，`maxLen` 就是最长递增子序列的长度。
     */
    public static int lengthOfLIS(int[] nums) {
        // 判空
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i] 表示以 nums[i] 结尾的最长递增子序列长度
        int[] dp = new int[nums.length];
        // 初始化，单个元素也构成递增子序列
        dp[0] = 1;
        // 最长递增子序列长度
        int maxLen = 1;

        for (int i = 1; i < nums.length; i++) {
            // 默认以当前元素为结尾的子序列长度为 1
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 说明可以将 nums[i] 加入以 nums[j] 结尾的子序列，从而构成一个更长的递增子序列
                    // 更新最长递增子序列长度
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 更新全局最长长度
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int lisLength = lengthOfLIS(nums);
        System.out.println("Length of Longest Increasing Subsequence: " + lisLength);
    }
}

