package slidingwindow;

import entity.ListNode;

/**
 * @author: Feng.Lee
 * 链表反转
 * @createDate: 2021/12/21
 * @version: 1.0
 */
public class ReverseListNode {

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    private static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "  ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        node1.next = new ListNode(5);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(8);
        node1.next.next.next.next = new ListNode(11);
        print(node1);
//        Node reverse = reverse(node1);
        ListNode reverse = reverseBetween(node1, 2, 4);
        print(reverse);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode();
        ListNode pre = dummyNode;
        pre.next = head;
        for (int i = 0; i < left -1; i++) {
            pre = pre.next;
        }
        ListNode next;
        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummyNode.next;
    }
}

