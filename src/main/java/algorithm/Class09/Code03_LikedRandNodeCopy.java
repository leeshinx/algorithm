package algorithm.Class09;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Feng.Lee
 * @createDate: 2022/1/19
 * @version: 1.0
 */
public class Code03_LikedRandNodeCopy {


    // 使用了容器map  适合笔试
    public static Node copyRandomList1(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        Node copyHead = null;
        Node copyCur = null;
        while(cur != null) {
            Node copy = new Node(cur.val);
            if (copyCur == null) {
                copyHead = copy;
            } else {
                copyCur.next = copy;
            }
            copyCur = copy;
            map.put(cur, copy);
            cur = cur.next;
        }

        cur = head;
        copyCur = copyHead;
        while(cur != null) {
            Node node = map.get(cur.random);
            copyCur.random = node;
            cur = cur.next;
            copyCur = copyCur.next;
        }
        return copyHead;
    }

    // 不使用容器map  适合面试
    public static Node copyRandomList2(Node head) {
        // 链表copy 原：{3,5,7,9} 后：{3,3,5,5,7,7,9,9}
        Node cur = head;
        while(cur != null) {
            Node copy = new Node(cur.val);
            Node next = cur.next;
            cur.next = copy;
            copy.next = next;
            cur = next;
        }

        // 随机指针copy
        cur = head;
        while(cur != null) {
            Node random = cur.random;
            if (random != null) {
                cur.next.random = random.next;
            }
            cur = cur.next.next;
        }

        // 链表拆分
        cur = head;
        Node copyHead = null;
        Node copyCur = null;
        print(head);
        while (cur!= null) {
            Node copy = cur.next;
            if (copyHead == null) {
                copyHead = copy;
            } else {
                copyCur.next = copy;
            }
            cur.next = copy.next;
            copyCur = copy;
            cur = copy.next;
        }
        print(head);
        return copyHead;
    }


    public static void main(String[] args) {
        Node node1 = new Node(7);
        node1.next = new Node(13);
        node1.next.next = new Node(11);
        node1.next.next.next = new Node(10);
        node1.next.next.next.next = new Node(1);

        node1.next.random = node1;
        node1.next.next.random = node1.next.next.next.next ;
        node1.next.next.next.random = node1.next.next;
        node1.next.next.next.next.random = node1;

        Node copy = copyRandomList2(node1);
        print(copy);

        System.out.println("====================================");
        print(node1);
    }


    private static void print(Node node1) {
        while (node1 != null) {
            if ( node1.random != null) {
                System.out.println(node1.val + "  " + node1.random.val);
            } else {
                System.out.println(node1.val + "  " + null);
            }
            node1 = node1.next;
        }
    }

}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}