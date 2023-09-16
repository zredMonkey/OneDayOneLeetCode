package com.example.onedayoneleetcode;

/**
 * 有6、7、8、9这4个数字,能组成多少个互不相同且无重复数字的三位数
 */
public class ThreeDigitNumbers {
    public static void main(String[] args) {
        int count = 0; // 用于计数符合条件的三位数的个数

        // 嵌套循环生成所有可能的三位数
        for (int i = 6; i <= 9; i++) { // 百位数循环
            for (int j = 6; j <= 9; j++) { // 十位数循环
                for (int k = 6; k <= 9; k++) { // 个位数循环
                    // 检查是否三个数字互不相同
                    if (i != j && i != k && j != k) {
                        int number = i * 100 + j * 10 + k; // 构造三位数
                        System.out.println(number); // 打印符合条件的三位数
                        count++;
                    }
                }
            }
        }

        System.out.println("共有 " + count + " 个符合条件的三位数。");
    }
}

