package com.example.onedayoneleetcode;


/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeSortedLists {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public class MergeSortedList {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // 创建一个虚拟头节点，它的next指向新链表的起始位置
            ListNode dummy = new ListNode(-1);
            ListNode current = dummy; // 用于迭代新链表

            // 遍历两个链表，比较节点值并逐个添加到新链表中
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    current.next = l1; // 将l1节点连接到新链表
                    l1 = l1.next; // 移动l1指针到下一个节点
                } else {
                    current.next = l2; // 将l2节点连接到新链表
                    l2 = l2.next; // 移动l2指针到下一个节点
                }
                current = current.next; // 移动current指针到新链表的末尾
            }

            // 将剩余的链表连接到新链表的末尾（如果有的话）
            if (l1 != null) {
                current.next = l1;
            } else {
                current.next = l2;
            }

            // 返回新链表的起始节点（跳过虚拟头节点）
            return dummy.next;
        }
    }

}
