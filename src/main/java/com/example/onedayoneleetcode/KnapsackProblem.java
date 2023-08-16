package com.example.onedayoneleetcode;


/**
 * 背包问题
 *
 *
 * 1. 我们可以使用动态规划来解决0-1背包问题。首先，定义一个二维数组`dp`，其中`dp[i][j]`表示在前i个物品中选择总重量不超过j的情况下的最大价值。
 * 2. 初始化`dp`数组，将第0行和第0列的值都设为0，表示没有物品或背包承载重量为0时的最大价值为0。
 * 3. 遍历物品和背包承载重量，对于每个物品和背包承载重量：
 * - 如果物品i的重量大于当前背包承载重量j，说明物品i不能放入背包，所以`dp[i][j]`的最大价值和`dp[i-1][j]`一样。
 * - 如果物品i的重量小于等于当前背包承载重量j，我们可以考虑是否将物品i放入背包。如果放入物品i，则背包中剩余的重量为`j - weights[i]`，所以最大价值为`values[i] + dp[i-1][j-weights[i]]`；如果不放入物品i，则最大价值为`dp[i-1][j]`。我们选择两者中较大的值作为`dp[i][j]`的最大价值。其中 `weight[i]` 为物品 i 的重量，`value[i]` 为物品 i 的价值。
 * 4. 最后`dp[n][W]`即为问题的解，其中n表示物品的个数，W表示背包的承载重量。
 */
public class KnapsackProblem {

    /**
     *
     * @param weights    物品的重量数组
     * @param values     价值数组
     * @param capacity   背包容量
     * @return 最大价值
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {

        int n = weights.length;

        // 创建一个二维数组来保存状态转移结果，dp[i][j] 表示在前 i 个物品中，背包容量为 j 时的最大价值
        int[][] dp = new int[n + 1][capacity + 1];

        // 填充 dp 数组，进行状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 如果当前物品的重量大于当前背包容量，无法放入，直接继承上一行的最大价值
                if (weights[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 否则，可以选择放入当前物品或不放入，取两者中的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        // 返回最终结果，即在考虑所有物品后，背包容量为 capacity 时的最大价值
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 5;
        int result = knapsack(weights, values, capacity);
        System.out.println("Maximum value: " + result);
    }
}


