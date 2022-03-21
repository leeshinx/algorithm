package slidingwindow;

import entity.ListNode;

/**
 * @author: Feng.Lee
 * @createDate: 2021/12/21
 * @version: 1.0
 */
public class TwoNumbers {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
//        node1.next = new ListNode(5);
//        node1.next.next = new ListNode(3);
//        node1.next.next.next = new ListNode(9);
//        node1.next.next.next.next = new ListNode(9);

        ListNode node2 = new ListNode(9);
        node2.next = new ListNode(9);
//        node2.next.next = new ListNode(7);

        print(node1);
        print(node2);
        ListNode num = addTwoNumbers(node1, node2);
        print(num);
    }

    public static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        int oneLength = nodeLength(node1);
        int twoLength = nodeLength(node2);
        ListNode first = oneLength >= twoLength ? node1 : node2;
        ListNode second = oneLength < twoLength ? node1 : node2;
        int up = 0;
        ListNode pre = first;
        ListNode result = pre;
        int max = Math.max(oneLength, twoLength);
        for (int i = 0; i < max; i++) {
            if (first != null && second != null) {
                int sum = first.value + second.value + up;
                first.value = sum % 10;
                up = sum / 10;
                pre = first;
                first = first.next;
                second = second.next;
                continue;
            }
            if (first != null) {
                int sum = first.value + up;
                first.value = sum % 10;
                up = sum / 10;
                pre = first;
                first = first.next;
                continue;
            }
        }
        if (up > 0) {
            pre.next = new ListNode(up);
        }
        return result;
    }

    private static int nodeLength(ListNode head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }

    private static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "  ");
            head = head.next;
        }
        System.out.println();
    }

}