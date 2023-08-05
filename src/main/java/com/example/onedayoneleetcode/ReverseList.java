package com.example.onedayoneleetcode;

/**
 * 题目：
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 输入：head = []
 * 输出：[]
 */
public class ReverseList {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseList(ListNode head) {
        // 初始化前一个节点为 null，当前节点为 head
        ListNode prev = null;
        ListNode current = head;

        // 迭代遍历链表
        while (current != null) {
            // 保存当前节点的下一个节点
            ListNode nextTemp = current.next;

            // 反转指针，将当前节点的下一个节点指向前一个节点
            current.next = prev;

            // 更新前一个节点和当前节点
            prev = current;
            current = nextTemp;
        }

        // 返回新的头节点，即原链表的最后一个节点
        return prev;
    }

}
