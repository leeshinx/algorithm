package algorithm.Class10;

import entity.ListNode;

/**
 * 找出两个可能有环的链表，相交的起始节点
 * @author: Feng.Lee
 * @createDate: 2022/1/20
 * @version: 1.0
 */
public class Code01_IntersectionNode {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode loopA = getLoopNode(headA);
        ListNode loopB = getLoopNode(headB);
        if (loopA == null && loopB == null) {
            // 无环链表相交的起始节点
            return getNonLoopIntersectionNode(headA, headB);
        }
        if (loopA != null && loopB !=null) {
            // 有环链表相交的始节点
            return getLoopIntersectionNode(headA, loopA, headB, loopB);
        }
        // 只有一个有环 则一定不想交
        return null;
    }


    // 返回环链表的 ，起始环节点， 否则返回null
    public static ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        // 有环的话，快慢指针会相遇 否则快指针指向null
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        /**
         * 快指针从head开始  快慢指针都一步一步走  再次相遇在起始环节点（ps: slow指针移动距离L1 + L2  fast指针移动距离 L1 + L2 + L3 + L2, fast指针速度是slow指针的2倍）
         * 所以 2 * (L1+L2) = L1+L2+L3+L2, 因此可以得出: L1 == L3
         */
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // 无环链表相交的起始节点
    private static ListNode getNonLoopIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        int dif = 0;
        while (curA != null && curA.next != null) {
            curA = curA.next;
            dif++;
        }

        ListNode curB = headB;
        while (curB != null && curB.next != null) {
            curB = curB.next;
            dif--;
        }

        if (curA != curA) {
            // 最后节点不相等 则不相交
            return null;
        }

        // 长链表先走
        curA = dif < 0 ? headB : headA;
        curB = curA == headA ? headB : headA;
        dif = Math.abs(dif);
        while (dif != 0) {
            curA = curA.next;
            dif--;
        }
        // 两个链表一起走 直到相遇
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }

        return curA;
    }

    // 有环链表相交的始节点  有三种可能性 一：66 二：_>——O  三：=O
    private static ListNode getLoopIntersectionNode(ListNode headA, ListNode loopA, ListNode headB, ListNode loopB) {
        if (loopA == loopB) {
            // 是第二种可能性 _>——O
            // 统计 两个链表长度差
            ListNode curA = headA;
            int dif = 0;
            while (curA != loopA) {
                curA = curA.next;
                dif++;
            }
            ListNode curB = headB;
            while (curB != loopB) {
                curB = curB.next;
                dif--;
            }

            // 长链表先走
            curA = dif < 0 ? headB : headA;
            curB = curA == headA ? headB : headA;
            dif = Math.abs(dif);
            while (dif != 0) {
                curA = curA.next;
                dif--;
            }
            // 两个链表一起走 直到相遇
            while (curA != curB) {
                curA = curA.next;
                curB = curB.next;
            }
            return curA;
        }
        // 让一个环转一圈 如果能碰到另一个环说明是情况三  =O
        ListNode cur = loopA.next;
        while (cur != loopA) {
            if (cur == loopB) {
                // 碰到另一个环起点
                return loopA;
            }
            cur = cur.next;
        }
        return null;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(0);
        node1.next.next = new ListNode(-4);

        ListNode loopNode = getLoopNode(node1);
        System.out.println(loopNode);
    }
}
