package com.example.onedayoneleetcode;

/**
 * 二叉树的最小深度是指从根节点到最近的叶子节点的最短路径长度。最小深度不是简单地计算根节点到叶子节点的节点个数，而是要考虑到存在只有单侧子树的情况。
 *
 * 思路如下：
 *
 * 1.对于空树（空节点），最小深度为0。
 * 2.对于只有一个子树的节点，应该取它的非空子树的最小深度。
 * 3.对于有两个子树的节点，应该取它的左右子树深度的最小值。
 * 因此，我们可以采用递归的方式来计算最小深度。递归地计算左右子树的最小深度，并取其中较小的值，然后再加上当前节点的深度1，即可得到最小深度。
 *
 *  当计算二叉树的最小深度时，我们可以使用广度优先搜索（BFS）来遍历树的每一层，直到找到最近的叶子节点
 */

import java.util.LinkedList;
import java.util.Queue;

// 定义二叉树节点类
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode(int val) {
//        this.val = val;
//    }
//}

public class MinimumDepthBinaryTree {
    // 求解二叉树的最小深度
    public int minDepth(TreeNode root) {
        // 如果根节点为空，表示深度为0
        if (root == null) {
            return 0;
        }

        // 使用队列来进行广度优先搜索
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 将根节点入队
        int depth = 0; // 初始化深度为0

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 当前层的节点数量

            depth++; // 进入下一层

            // 遍历当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll(); // 出队一个节点

                // 如果找到最近叶子节点，返回当前深度
                if (node.left == null && node.right == null) {
                    return depth;
                }

                // 将当前节点的非空子节点入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        // 如果没有找到叶子节点，返回整棵树的高度
        return depth;
    }

    public static void main(String[] args) {
        // 构建一个示例二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        // 创建MinimumDepthBinaryTree对象
        MinimumDepthBinaryTree minDepthBinaryTree = new MinimumDepthBinaryTree();

        // 计算示例二叉树的最小深度
        int minDepth = minDepthBinaryTree.minDepth(root);
        System.out.println("二叉树的最小深度: " + minDepth);
    }
}


