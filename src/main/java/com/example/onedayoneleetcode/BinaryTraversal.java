package com.example.onedayoneleetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTraversal {

    // 定义二叉树节点类
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // 前序遍历：根 - 左 - 右
    public static void preorderTraversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");  // 访问根节点
        preorderTraversal(root.left);      // 递归遍历左子树
        preorderTraversal(root.right);     // 递归遍历右子树
    }

    // 前序遍历（非递归） 根 - 左 - 右
    public static void preorderTraversalN(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

/*----------------------------------------------------------------------------------------------------------------------------- */
    // 中序遍历：左 - 根 - 右
    public static void inorderTraversal(TreeNode root) {
        if (root == null) return;
        inorderTraversal(root.left);       // 递归遍历左子树
        System.out.print(root.val + " ");  // 访问根节点
        inorderTraversal(root.right);      // 递归遍历右子树
    }

    // 中序遍历（非递归）  左 - 根 - 右
    public static void inorderTraversalN(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        // curr部位==不为null且栈不为空
        while (curr != null || !stack.isEmpty()) { // !!! 主要curr != null
            while (curr != null) {
                stack.push(curr);
                curr = curr.left; // 将左子树全部压入栈
            }
            curr = stack.pop(); // 弹出最左边的节点，即当前子树最小值
            System.out.print(curr.val + " "); // 访问当前节点
            curr = curr.right; // 转向右子树
        }
    }

    /*-----------------------------------------------------------------------------------------------------------------*/

    // 后序遍历：左 - 右 - 根
    public static void postorderTraversal(TreeNode root) {
        if (root == null) return;
        postorderTraversal(root.left);     // 递归遍历左子树
        postorderTraversal(root.right);    // 递归遍历右子树
        System.out.print(root.val + " ");  // 访问根节点
    }

    // 后序遍历（非递归）
    // 在后序遍历中，我们使用一个额外的栈来存储节点，按照根-右-左的顺序将节点压入栈，然后再从这个临时栈中弹出节点，就可以得到后序遍历的结果
    public static void postorderTraversalN(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        // 额外的栈进行存储
        Stack<TreeNode> output = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.push(node); // 使用另一个栈进行临时存储

            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        while (!output.isEmpty()) {
            System.out.print(output.pop().val + " "); // 从临时栈中弹出节点，实现后序遍历
        }
    }

/*------------------------------------------------------------------------------------------------------*/

    // 层序遍历
    public static void levelOrderTraversal(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        // 加入队列尾部
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 获取队列头部元素
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");  // 访问当前节点

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    // 构建二叉树:       4
    //                /   \
    //               2     6
    //              / \   / \
    //             1   3 5   7
    // 层序遍历（非递归）
    public static void levelOrderTraversalN(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " "); // 访问当前节点

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    /*--------------------------------------------------------------------------------------------------------------*/


    public static void main(String[] args) {
        // 构建二叉树:       1
        //                /   \
        //               2     3
        //              / \   / \
        //             4   5 6   7
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("前序遍历:");
        preorderTraversal(root);

        System.out.println("\n中序遍历:");
        inorderTraversal(root);

        System.out.println("\n后序遍历:");
        postorderTraversal(root);

        System.out.println("\n层序遍历:");
        levelOrderTraversal(root);
    }
}
