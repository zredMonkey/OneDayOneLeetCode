package com.example.onedayoneleetcode;

/**
 *  字符串翻转
 *
 *  当使用双指针方法时，我们可以通过在字符串的两端设置左指针和右指针，并交换它们指向的字符来实现字符串的翻转
 *
 *  在这个方法中，我们首先将输入的字符串 str 转换为字符数组 charArray，这样就可以通过索引访问和修改其中的字符。接着，我们初始化两个指针 left 和 right 分别指向字符串的起始位置和末尾位置。
 * 然后，我们进入一个循环，循环的条件是左指针 left 小于右指针 right，也就是说，左指针和右指针没有相遇。在循环中，我们通过交换左指针和右指针所指向的字符，来实现字符的翻转。具体地，
 * 我们使用一个临时变量 temp 来保存左指针指向的字符，然后将右指针指向的字符赋值给左指针指向的位置，最后将临时变量中保存的字符赋值给右指针指向的位置，这样完成了两个字符的交换。
 * 接着，我们将左指针 left 向右移动一步，指向下一个字符，同时将右指针 right 向左移动一步，指向上一个字符，以便进行下一轮交换。循环会继续进行，直到左指针 left 不再小于右指针
 * right此时字符串的翻转完成。
 *
 * 最后，我们将字符数组 charArray 转换回字符串，并返回翻转后的结果。
 */


public class ReverseString {

    // 定义一个方法，用于实现字符串的翻转
    public static String reverseString(String str) {
        // 将字符串转换为字符数组，方便后续交换字符
        char[] charArray = str.toCharArray();

        // 初始化左指针指向字符串的起始位置
        int left = 0;
        // 初始化右指针指向字符串的末尾位置
        int right = str.length() - 1;

        // 当左指针小于右指针时，执行循环（左指针和右指针相遇时循环结束）
        while (left < right) {
            // 交换左指针和右指针指向的字符
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // 移动左指针向右移动一步，指向下一个字符
            left++;
            // 移动右指针向左移动一步，指向上一个字符
            right--;
        }

        // 将字符数组转换回字符串并返回
        return new String(charArray);
    }

    public static void main(String[] args) {
        String originalString = "Hello, World!";
        String reversedString = reverseString(originalString);
        System.out.println("Original String: " + originalString);
        System.out.println("Reversed String: " + reversedString);
    }
}
