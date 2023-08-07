package com.example.onedayoneleetcode;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class ReverseBetweenLinkedList {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }

        // 创建虚拟节点，并将其指向链表头部
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 定位到第 m-1 个节点，用 prev 指针指向它
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }

        // 记录 prev 初始位置，用于连接反转后的子链表
        ListNode startPrev = prev;

        // 开始反转操作，需要反转 n-m+1 次
        ListNode curr = prev.next;
        ListNode nextTemp;

        for (int i = 0; i < n - m + 1; i++) {
            nextTemp = curr.next;  // 记录当前节点的下一个节点
            curr.next = prev;      // 反转操作：将当前节点的 next 指向 prev
            prev = curr;           // prev 向前移动
            curr = nextTemp;       // curr 向前移动
        }

        // 连接反转后的子链表与原链表
        startPrev.next.next = curr;
        startPrev.next = prev;

        // 返回虚拟节点的 next，即为反转后的链表头部
        return dummy.next;
    }

    public static void main(String[] args) {
        // 创建一个示例链表: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ReverseBetweenLinkedList solution = new ReverseBetweenLinkedList();
        int m = 2;
        int n = 4;
        ListNode reversedHead = solution.reverseBetween(head, m, n);

        // 输出反转后的链表
        ListNode current = reversedHead;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}
