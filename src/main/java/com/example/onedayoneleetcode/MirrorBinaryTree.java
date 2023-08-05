package com.example.onedayoneleetcode;

/**
 * 将二叉树变为镜像二叉树，其实就是将二叉树中的每个节点的左右子树进行交换，形成一个镜像对称的二叉树。换句话说，就是将左子树变为右子树，右子树变为左子树。
 *
 * 思路如下：
 *
 * 1.从根节点开始，递归地对每个节点进行处理。
 * 2.对于每个节点，首先交换其左右子树。
 * 3.然后递归地处理其左子树和右子树，分别将其左子树和右子树转化为镜像二叉树。
 * 通过以上步骤，最终整棵树就变为镜像二叉树了。
 *
 * */
// 定义二叉树节点类
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class MirrorBinaryTree {
    // 将二叉树变为镜像二叉树的函数
    public TreeNode mirrorTree(TreeNode root) {
        // 递归的终止条件：如果节点为空，则直接返回 null
        if (root == null) {
            return null;
        }

        // 交换当前节点的左子树和右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归处理左子树和右子树，使它们也成为镜像二叉树
        mirrorTree(root.left);
        mirrorTree(root.right);

        // 返回变换后的根节点
        return root;
    }

    // 辅助函数：用于中序遍历打印二叉树
    public void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        // 中序遍历顺序：左子树 -> 根节点 -> 右子树
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        // 构建一个示例二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // 创建MirrorBinaryTree对象
        MirrorBinaryTree mirrorBinaryTree = new MirrorBinaryTree();

        System.out.println("原始二叉树:");
        mirrorBinaryTree.inorderTraversal(root);
        System.out.println();

        // 将二叉树变为镜像二叉树
        TreeNode mirroredRoot = mirrorBinaryTree.mirrorTree(root);

        System.out.println("镜像二叉树:");
        mirrorBinaryTree.inorderTraversal(mirroredRoot);
    }
}

