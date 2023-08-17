package com.example.onedayoneleetcode;


/**
 * 最长公共子序列
 *
 * 1. 我们可以使用动态规划来解决LCS问题。首先，定义一个二维数组`dp`，其中`dp[i][j]`表示序列A的前i个元素和序列B的前j个元素的最长公共子序列的长度。
 * 2. 初始化`dp`数组，将第0行和第0列的值都设为0，表示当一个序列为空时，与任何序列的最长公共子序列长度都为0。
 * 3. 遍历两个序列的元素，对于每个元素`A[i]`和`B[j]`：
 * -  如果`A[i]`和`B[j]`相等，说明它们可以作为最长公共子序列的一部分，因此`dp[i][j]`的值应该是`dp[i-1][j-1] + 1`，即在之前的最长公共子序列长度上加1。
 * -  如果`A[i]`和`B[j]`不相等，说明它们不能同时出现在最长公共子序列中，此时我们需要考虑舍弃其中一个元素，即取`dp[i-1][j]`和`dp[i][j-1]`中的较大值作为`dp[i][j]`的值。
 * 4. 最后，`dp[n][m]`即为序列A和B的最长公共子序列的长度，其中n和m分别是序列A和B的长度。
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 创建一个二维数组 dp，dp[i][j] 表示 text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];

        // 填充 dp 数组，进行状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果当前字符相同，说明可以将这个字符纳入公共子序列中，长度加一
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 否则，选择不使用当前字符，取前面最长的公共子序列长度
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回 text1 和 text2 的最长公共子序列长度
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        int result = longestCommonSubsequence(text1, text2);
        System.out.println("Longest Common Subsequence: " + result);
    }
}

