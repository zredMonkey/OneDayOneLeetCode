package com.example.onedayoneleetcode;

import java.util.Arrays;

/**
 * 零钱兑换--贪心算法
 */

public class CoinChangeGreedy {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5}; // 零钱的面额
        int amount = 11; // 要兑换的金额
        int numCoins = coinChange(coins, amount);
        System.out.println("最少需要的硬币数：" + numCoins);
    }

    /**
     *
     * @param coins    硬币面额
     * @param amount   要兑换的金额
     * @return 最少使用硬币数目
     */
    public static int coinChange(int[] coins, int amount) {
        // 面额按从小到大排序
        Arrays.sort(coins);

        // 记录硬币数量
        int count = 0;
        // 从最大面额的硬币开始尝试
        int index = coins.length - 1;

        while (amount > 0 && index >= 0) {

            if (coins[index] <= amount) {
                // 尝试使用当前面额的硬币数量
                int numCoins = amount / coins[index];
                count += numCoins;
                amount -= numCoins * coins[index];
            }
            // 尝试下一个面额的硬币
            index--;
        }

        // 如果剩余金额为0，返回硬币数量，否则返回-1表示无法兑换
        return amount == 0 ? count : -1;
    }
}

