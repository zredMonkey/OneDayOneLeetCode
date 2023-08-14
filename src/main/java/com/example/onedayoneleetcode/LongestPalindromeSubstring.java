package com.example.onedayoneleetcode;

/**
 * 最长回文字串--动态规划
 *
 *
 */
public class LongestPalindromeSubstring {

    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // dp[i][j]表示s的子串从i到j是否为回文子串

        int start = 0; // 记录最长回文子串的起始位置
        int maxLength = 1; // 记录最长回文子串的长度

        // 所有单个字符都是回文子串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 检查长度为2的子串
        for (int i = 0; i < n - 1; i++) {
            // 如果相邻两个字符相等，那么它们是回文子串
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // 检查长度大于2的子串
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // 子串的结束位置
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = len;
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String input = "babad";
        String longestPalindrome = longestPalindrome(input);
        System.out.println("Longest Palindrome Substring: " + longestPalindrome);
    }
}

