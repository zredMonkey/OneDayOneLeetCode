package com.example.onedayoneleetcode;


/**
 * 回文子串个数
 */
public class CountPalindromicSubstrings {
    public static int countSubstrings(String s) {
        int n = s.length();
        int count = 0; // 记录回文子串的个数
        boolean[][] dp = new boolean[n][n]; // dp[i][j]表示s的子串从i到j是否为回文子串

        // 所有单个字符都是回文子串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        // 检查长度为2的子串
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        // 检查长度大于2的子串
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // 子串的结束位置
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String input = "abc";
        int palindromeCount = countSubstrings(input);
        System.out.println("Palindrome Substrings Count: " + palindromeCount);
    }
}

