package com.example.onedayoneleetcode;

/**
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 *
 */
public class RegularExpressionMatching {


    /**
     * 1. `dp[i][j]`表示s的前i个字符和p的前j个字符是否匹配。
     * 2. 初始化：空字符串和空正则表达式匹配，`dp[0][0] = true`。
     * 3. 处理空正则表达式可以匹配的情况：如果p的某个字符是'*'，那么它可以匹配0次，将`dp[0][j]`设置为`dp[0][j-2]`。
     * 4. 填充dp表格：根据字符匹配和'*'的特性，更新`dp[i][j]`的值。
     * 5. 最终结：`dp[m][n]`表示s的全部字符和p的全部字符是否匹配。
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j]表示s的前i个字符和p的前j个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 空字符串和空正则表达式匹配
        dp[0][0] = true;

        // 处理空正则表达式可以匹配的情况
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填充dp表格
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char prevPc = p.charAt(j - 2);
                    if (prevPc == sc || prevPc == '.') {
                        // 匹配0次、1次或多次
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j] || dp[i - 1][j - 2];
                    } else {
                        // 匹配0次
                        dp[i][j] = dp[i][j - 2];
                    }
                }

            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "mississippi";
        String p = "mis*is*p*.";

        boolean result = isMatch(s, p);
        System.out.println("Is match: " + result);
    }
}

