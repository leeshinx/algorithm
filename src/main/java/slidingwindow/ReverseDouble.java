package slidingwindow;

/**
 * @author: Feng.Lee
 * 双向链表反转
 * @createDate: 2021/12/21
 * @version: 1.0
 */
public class ReverseDouble {

    public static void main(String[] args) {
        NodeDouble node1 = new NodeDouble(1);
        NodeDouble node2 = new NodeDouble(2);
        node1.next = node2;
        node2.last = node1;
        NodeDouble node3 = new NodeDouble(3);
        node2.next = node3;
        node3.last = node2;
        NodeDouble node4 = new NodeDouble(8);
        node3.next = node4;
        node4.last = node3;

        print(node1);
        NodeDouble reverse = reverseDoubleList(node1);
        NodeDouble reverse1 = reverseDoubleList(null);
        print(reverse);
    }

    private static NodeDouble reverseDoubleList (NodeDouble head) {
        NodeDouble pre = null;
        NodeDouble next = null;
        while(head != null){
            next = head.next;
            head.last = next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    private static void print(NodeDouble head) {
        NodeDouble cur = null;
        while (head != null) {
            System.out.print(head.value + "  ");
            cur = head;
            head = head.next;
        }
        System.out.println();

        while (cur != null) {
            System.out.print(cur.value + "  ");
            cur = cur.last;
        }
        System.out.println();
    }
}

class NodeDouble{
    int value;
    NodeDouble last;
    NodeDouble next;

    public NodeDouble(int value) {
        this.value = value;
    }
}
