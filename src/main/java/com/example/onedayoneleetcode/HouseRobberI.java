package com.example.onedayoneleetcode;

/**
 * 打家劫舍（House Robber）问题：如不相邻的房屋偷窃的最大金额。
 *
 *
 */

public class HouseRobberI {

    /**
     * 1. 我们可以使用动态规划来解决这个问题。定义一个一维数组dp，其中dp[i]表示偷窃前i个房屋能够得到的最大金额。
     * 2. 初始化dp数组，dp[0]为第一个房屋的金额，dp[1]为第二个房屋和第一个房屋金额的较大值。
     * 3. 遍历数组，对于每个房屋i，考虑两种情况：
     * - 偷窃第i个房屋：则最大金额为dp[i-2]+nums[i]，即偷窃第i-2个房屋的最大金额加上第i个房屋的金额。
     * - 不偷窃第i个房屋：则最大金额为dp[i-1]，即偷窃前i-1个房屋的最大金额。
     * - 取两种情况中的较大值作为dp[i]的值。
     * 4. 最终，dp[n-1]即为偷窃到的最大金额，其中n为房屋的个数。
     *
     *
     * @param nums 一个代表每个房屋存放金额的非负整数数组
     * @return
     */
    public static int rob(int[] nums) {

        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return nums[0];
        }

        // dp[i] 表示偷窃前 i 个房屋能够获得的最大金额
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // 在当前房屋偷窃或不偷窃之间选择最大值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        // 返回最后一个房屋偷窃或不偷窃的最大金额
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        int result = rob(nums);
        System.out.println("Maximum amount: " + result);
    }
}

