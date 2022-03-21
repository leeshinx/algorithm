package algorithm.Class09;

import entity.ListNode;

/**
 * @author: Feng.Lee
 * @createDate: 2022/1/18
 * @version: 1.0
 */
public class Code02_SmallerEqualBigger {


    private static ListNode smallerEqualBigger(ListNode head, int n) {
        ListNode sH = null;
        ListNode sT = null;
        ListNode eH = null;
        ListNode eT = null;
        ListNode bH = null;
        ListNode bT = null;
        ListNode cur = head;
        while (cur != null) {
            // 小于
            if (cur.value < n) {
                if (sH == null) {
                    sH = cur;
                    sT = cur;
                } else {
                    sT.next = cur;
                    sT = cur;
                }
            }

            if (cur.value == n) {
                if (eH == null) {
                    eH = cur;
                    eT = cur;
                } else {
                    eT.next = cur;
                    eT = cur;
                }
            }

            if (cur.value > n) {
                if (bH == null) {
                    bH = cur;
                    bT = cur;
                } else {
                    bT.next = cur;
                    bT = cur;
                }
            }
        }

        // 拼接 小于 等于 大于区
        if (sH != null) {
            sT.next = eH;
            // 找到小于区 和 等于区 的尾巴
            eT = eT == null ? sT : eT;
        }

        if (eT != null) {
            eT.next = bH;
        }

        return sH != null ? sH : (eH != null ? eH : bH);
    }

}
