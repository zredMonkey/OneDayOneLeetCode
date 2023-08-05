package com.example.onedayoneleetcode;

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

/**
 * 判断二叉树是否是平衡二叉树，需要计算每个节点的左子树高度和右子树高度，并判断它们的高度差是否小于等于1
 *
 *
 */
public class BalancedBinaryTree {

    // 判断二叉树是否是平衡二叉树的函数
    public boolean isBalanced(TreeNode root) {
        // 使用递归计算树的高度，并在计算的过程中判断是否平衡
        return checkBalance(root) != -1;
    }

    // 辅助函数：用于计算树的高度并判断是否平衡
    private int checkBalance(TreeNode node) {
        // 递归的终止条件：如果节点为空，高度为0
        if (node == null) {
            return 0;
        }

        // 递归计算左子树高度
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) {
            return -1; // 左子树不平衡，直接返回
        }

        // 递归计算右子树高度
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) {
            return -1; // 右子树不平衡，直接返回
        }

        // 计算当前节点的高度，如果左右子树高度差大于1，则返回-1表示不平衡
        //  Math.abs 返回一个数的绝对值
        if (Math.abs(leftHeight - rightHeight) > 1) {
            // 不平衡
            return -1;
        }

        // 返回当前节点的高度  ！！！
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        // 构建一个示例平衡二叉树
        TreeNode balancedRoot = new TreeNode(1);
        balancedRoot.left = new TreeNode(2);
        balancedRoot.right = new TreeNode(3);
        balancedRoot.left.left = new TreeNode(4);
        balancedRoot.left.right = new TreeNode(5);

        // 构建一个示例非平衡二叉树
        TreeNode unbalancedRoot = new TreeNode(1);
        unbalancedRoot.left = new TreeNode(2);
        unbalancedRoot.right = new TreeNode(3);
        unbalancedRoot.left.left = new TreeNode(4);
        unbalancedRoot.left.left.left = new TreeNode(5);

        // 创建BalancedBinaryTree对象
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();

        // 判断示例平衡二叉树是否平衡
        System.out.println("示例平衡二叉树是否平衡: " + balancedBinaryTree.isBalanced(balancedRoot));

        // 判断示例非平衡二叉树是否平衡
        System.out.println("示例非平衡二叉树是否平衡: " + balancedBinaryTree.isBalanced(unbalancedRoot));
    }
}

