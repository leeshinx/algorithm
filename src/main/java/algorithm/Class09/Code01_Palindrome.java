package algorithm.Class09;

import entity.ListNode;

/**
 * @author: Feng.Lee
 * 234. 回文链表
 * @createDate: 2022/1/18
 * @version: 1.0
 */
public class Code01_Palindrome {

    // 笔试时可以使用容器 栈，面试最好不使用容器
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next ==null) {
            return true;
        }
        // 快慢指针找链表中点 偶数个返回中点前一个
        ListNode quilt = head;
        ListNode slow = head;
        while (quilt.next != null && quilt.next.next != null) {
            slow = slow.next;
            quilt = quilt.next.next;
        }

        // 从中点后开始反转链表
        ListNode pre = slow;
        ListNode cur = pre.next;
        pre.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 回文比对
        boolean flag = true;
        ListNode head1 = head;
        ListNode head2 = pre;
        while (head1 != null && head2 != null) {
            if (head1.value != head2.value) {
                flag = false;
                break;
            }

            head1 = head1.next;
            head2 = head2.next;
        }

        // 把中点后反转的链表恢复
        cur = pre.next;
        pre.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return flag;
    }

}
