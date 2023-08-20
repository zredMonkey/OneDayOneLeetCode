package com.example.onedayoneleetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列---回溯算法
 */

public class PermutationsBacktracking {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);
        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

    // 初始化了一个空的结果列表
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 全排列
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }


    /**
     * 回溯函数，用于生成全排列
     * @param nums      数组 【1，2，3】
     * @param track     记录路径  进入一个新节点，要加入这个节点的元素；退后一个节点时，要删除这个节点元素
     * @param result    存储全排列结果
     */
    private static void backtrack(int[] nums, List<Integer> track, List<List<Integer>> result) {
        // 到达叶子节点，将路径装入结果列表
        if (track.size() == nums.length) {
            result.add(new ArrayList<>(track));
            return;
        }

        // 尝试每个未使用的元素
        for (int num : nums) {
            if (track.contains(num)) {
                // 如果当前数字已经在排列中，跳过
                continue;
            }
            track.add(num);
            // 递归调用
            backtrack(nums, track, result);
            // 回溯，移除最后一个元素
            track.remove(track.size() - 1);
        }
    }
}

