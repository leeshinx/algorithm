package slidingwindow;

import entity.ListNode;

/**
 * @author: Feng.Lee
 * 两个有序链表和并
 * @createDate: 2021/12/21
 * @version: 1.0
 */
public class NodeMerge {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(9);
        node1.next.next.next.next = new ListNode(9);

        ListNode node2 = new ListNode(7);
        node2.next = new ListNode(8);
        node2.next.next = new ListNode(7);

        print(node1);
        print(node2);
        ListNode num = merge(node1, node2);
        print(num);
    }

    public static ListNode merge(ListNode node1, ListNode node2) {
        ListNode empNode = new ListNode();
        ListNode cur = empNode;
        while(node1 != null && node2 != null) {
            if (node1.value <= node2.value) {
                empNode.next = node1;
                node1 = node1.next;
            } else {
                empNode.next = node2;
                node2 = node2.next;
            }
            empNode = empNode.next;
        }
        empNode.next = node1 != null ? node1 : node2;
        return cur.next;
    }


    private static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "  ");
            head = head.next;
        }
        System.out.println();
    }

}