package com.example.onedayoneleetcode;

/**
 * 这是一个经典的正则表达式匹配问题，可以使用动态规划（Dynamic Programming）来解决。
 * 动态规划的思想是将问题分解为子问题，并存储子问题的解，以避免重复计算。在这个问题中，
 * 我们可以定义一个二维的DP数组来表示匹配状态。
 *
 *
 *
 * 这段代码中，我们定义了一个二维的DP数组 dp，
 * 其中 dp[i][j] 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配。然后，我们初始化 DP 数组，
 * 处理正则表达式中的 *，并填充 DP 数组来判断匹配状态。
 *
 * 动态规划的时间复杂度为 O(mn)，其中 m 和 n 分别是字符串 s 和正则表达式 p 的长度。
 * 空间复杂度也是 O(mn)，因为我们使用了一个二维 DP 数组。
 *
 * 这个算法可以有效地处理包含 . 和 * 的正则表达式匹配问题。
 *
 */
public class IsMatch {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // 空字符串与空正则表达式匹配为true

        // 初始化第一行，处理正则表达式中的'*'
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; // '*'前面的字符出现0次
            }
        }

        // 填充DP数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2]; // '*'前面的字符出现0次
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                        // 匹配0次、1次、多次
                    }
                }
            }
        }

        return dp[m][n];
    }

}
