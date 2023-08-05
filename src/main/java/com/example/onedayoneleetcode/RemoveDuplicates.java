package com.example.onedayoneleetcode;

/**
 * 升序数组的遍历
 *
 * 首先初始化了慢指针 slow 和快指针 fast，然后使用快指针遍历整个数组。当遇到不重复的元素时，将该元素移到慢指针位置，
 * 然后慢指针递增。最终，通过复制原数组的前部分，创建了去重后的数组。
 *
 * 这种方法在 O(n) 的时间复杂度内实现了数组元素去重，且不需要额外的数据结构，空间复杂度为 O(1)。
 */
public class RemoveDuplicates {


    public static void main(String[] args) {
        int[] originalArray = {1, 2, 3, 3, 5,7,7,7,9};

        int[] uniqueArray = removeDuplicates(originalArray);

        System.out.print("Original Array: ");
        printArray(originalArray);

        System.out.print("Unique Array: ");
        printArray(uniqueArray);
    }

    public static int[] removeDuplicates(int[] arr) {
        // 已去重
        if (arr.length <= 1) {
            return arr;
        }
        int slow = 0; // 初始化慢指针，指向数组的第一个元素
        int fast = 1; // 初始化快指针，指向数组的第二个元素

        // 快指针遍历
        while (fast < arr.length) {
            if (arr[slow] != arr[fast]) {
                 slow = slow + 1; // 如果当前元素与前一个元素不相同，慢指针递增
                 arr[slow] = arr[fast]; // 将不重复的元素移到慢指针位置
            }
            fast = fast + 1; // 快指针递增
        }
        // 创建去重后的数组
        int[] uniqueArray = new int[slow + 1];
        // 将原数组的前部分复制到去重后的数组中
        System.arraycopy(arr, 0, uniqueArray, 0, slow + 1);
        return uniqueArray;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
