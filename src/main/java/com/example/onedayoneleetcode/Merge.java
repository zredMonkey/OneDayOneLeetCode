package com.example.onedayoneleetcode;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * 示例 3：
 *
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 *
 * 逆向双指针
 *
 *
 * 时间复杂度：O(m+n)O(m+n)O(m+n)。 指针移动单调递减，最多移动 m+nm+nm+n 次，因此时间复杂度为 O(m+n)O(m+n)O(m+n)。
 * 空间复杂度：O(1)O(1)O(1)。 直接对数组 nums1\textit{nums}_1nums
 *   原地修改，不需要额外空间。
 */

public class Merge {

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        if (m < 0 || n < 0) {
            return null;
        }
        // p1 p2 分别为两个数组从后向前移动的指针
        int p1 = m - 1;
        int p2 = n - 1;

        int tail = m + n - 1;
        int curr;

        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                // nums1为0
                curr = nums2[p2--];
            } else if(p2 == -1) {
                // nums2 为0
                curr = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                curr = nums1[p1--];
            } else {
                curr = nums2[p2--];
            }
            // 将较大的值放入num1数组后半部分
            nums1[tail--] = curr;
        }
        return nums1;

    }

    public static void main(String[] args) {
        int[] num1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        int[] merge = merge(num1, 3, nums2, 3);
        for (int temp: merge) {
            System.out.print(temp + "   ");
        }

    }

}