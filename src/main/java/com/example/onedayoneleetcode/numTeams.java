package com.example.onedayoneleetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @date 2021/4/11 21:51
 *
 *  n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
 * 每 3 个士兵可以组成一个作战单位，分组规则如下：
 *
 *  - 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
 *  - 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
 * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
 *
 * 示例 1：
 * 输入：rating = [2,5,3,4,1]
 * 输出：3
 * 解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
 *
 * 示例 2：
 * 输入：rating = [2,1,3]
 * 输出：0
 * 解释：根据题目条件，我们无法组建作战单位。
 *
 * 提示：
 *  - n == rating.length
 *  - 3 <= n <= 1000
 *  - 1 <= rating[i] <= 10^5
 *  - rating 中的元素都是唯一的
 **/
public class numTeams {

    int maxN;
    int[] c;
    List<Integer> disc;
    int[] iLess;
    int[] iMore;
    int[] kLess;
    int[] kMore;

     /**
       * 前置知识
      *
      *  - 离散化思想，在不改变数据相对大小的条件下，对数据进行相应的缩小。
      *  - 树状数组（二元索引树），一种动态维护前缀和的数据结构。
      *
      * 思路:
      * 考虑优化方法二中求 i{less}、k{more}、i{more}、k{less}的过程。在方法二中我们使用了枚举来求解这四个量，单次枚举的时间代价是O(N)。
      * 假设我们有一个桶数组，索引 i 的值为 1 就说明存在元素 i，为 0 就说明不存在元素 i，那么该桶数组的前缀和preffixSum[i−1] 就表示当前比 i小的数的个数，
      * 我们只需要用树状数组动态维护这个前缀和，就可以把单次的时间代价从O(N) 优化到 O(logN)。
      * 我们对 rating 数组做两次遍历，一次从前向后，一次从后向前。从前向后的时候，对于每一个 rating[i] （记为 x），
      * 求到上述桶数组下标 x−1 的前缀和，即 i{less}，记 rating 数组中出现的最大值为 r{max}，用 r{max}的前缀和减去 x 位置的前缀和即可得到 i{more} .
      * 从后向前的那次遍历同理。
      *
      * 思考：仅仅这样做真的可以单次计算变成logN 吗？ 我们知道树状数组修改和查询的时间代价和树状数组的长度相关，也就是这里的 r{max}（它最大可以到 10^5），
      * 所以这里单次查询的代价是O(logrmax)。实际上 rating 的长度最大只有 200，也就是这个树状数组中的「有效位置」最多只有 200 个，
      * 所以我们不用开辟 10^5的长度，只需要开辟 200 的长度，通过离散化的方法缩小值域，这样就可以把单次的时间代价变成 O(logN)。
      * 由于这里没有重复的数字，所以只需要对 rating 数组中的数进行排序，然后二分获取离散化之后的值即可，单次二分的时间代价也是O(logN)。
      *
      *
      - 时间复杂度：O(NlogN)。离散化过程中对数组排序的时间代价是O(NlogN)。两次遍历，每次 N 个元素，
        对于每个元素做一次O(logN) 的离散值查询和 O(3×logN) 的树状数组操作，故渐进时间复杂度为O(NlogN+2×N×(logN+3logN))=O(NlogN)。
      - 空间复杂度：O(N)。这里用了长度为 N 的数组（6 个）最为辅助空间，渐进空间复杂度为 O(N)。
       **/
    public int numTeams(int[] rating) {
        int n = rating.length;
        maxN = n + 2;
        c = new int[maxN];
        disc = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            disc.add(rating[i]);
        }
        disc.add(-1);
        Collections.sort(disc);
        iLess = new int[n];
        iMore = new int[n];
        kLess = new int[n];
        kMore = new int[n];

        for (int i = 0; i < n; ++i) {
            int id = getId(rating[i]);
            iLess[i] = get(id);
            iMore[i] = get(n + 1) - get(id);
            add(id, 1);
        }

        c = new int[maxN];
        for (int i = n - 1; i >= 0; --i) {
            int id = getId(rating[i]);
            kLess[i] = get(id);
            kMore[i] = get(n + 1) - get(id);
            add(id, 1);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += iLess[i] * kMore[i] + iMore[i] * kLess[i];
        }

        return ans;
    }

    public int getId(int target) {
        int low = 0, high = disc.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (disc.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int get(int p) {
        int r = 0;
        while (p > 0) {
            r += c[p];
            p -= lowbit(p);
        }
        return r;
    }

    public void add(int p, int v) {
        while (p < maxN) {
            c[p] += v;
            p += lowbit(p);
        }
    }

    public int lowbit(int x) {
        return x & (-x);
    }


     /**
      * 我们也可以枚举三元组 (i, j, k)中的 j，它是三元组的中间点。在这之后，我们统计：
      *
      *  - 出现在位置 j左侧且比 j 评分低的士兵数量i{less};
      *  - 出现在位置 j左侧且比 j评分高的士兵数量 i{more}；
      *  - 出现在位置 j右侧且比 j评分低的士兵数量 k{less}；
      *  - 出现在位置 j右侧且比 j评分高的士兵数量 k{more}。
      *
      * 这样以来，任何一个出现在i{less}中的士兵 i，以及出现在k{more}中的士兵 k，都可以和 j 组成一个严格单调递增的三元组
      * 同理，任何一个出现在 i{more}中的士兵 i，以及出现在k{less}中的士兵 k，都可以和 j 组成一个严格单调递减的三元组。
      * 因此，以 j 为中间点的三元组的数量为：
      * i{less} ∗ k{more} + i{more} ∗ k{less}
      * 我们将所有的值进行累加即可得到答案
      *
      *  - 时间复杂度：O(N^2)，其中 N 是数组ratings[] 的长度。我们需要使用一重循环枚举三元组中的 j，另一重循环计算 i{less}，i{more}，k{less}和 k{more}。
      *  - 空间复杂度：O(1)。
       **/
//    public int numTeams(int[] rating) {
//        int n = rating.length;
//        int ans = 0;
//        // 枚举三元组中的 j
//        for (int j = 1; j < n - 1; ++j) {
//            int iless = 0, imore = 0;
//            int kless = 0, kmore = 0;
//            for (int i = 0; i < j; ++i) {
//                if (rating[i] < rating[j]) {
//                    ++iless;
//                }
//                // 注意这里不能直接写成 else
//                // 因为可能有评分相同的情况
//                else if (rating[i] > rating[j]) {
//                    ++imore;
//                }
//            }
//            for (int k = j + 1; k < n; ++k) {
//                if (rating[k] < rating[j]) {
//                    ++kless;
//                } else if (rating[k] > rating[j]) {
//                    ++kmore;
//                }
//            }
//            ans += iless * kmore + imore * kless;
//        }
//        return ans;
//    }

}
