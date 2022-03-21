package algorithm.Class11;

/**
 * @author: Feng.Lee
 * 找到对应节点的后继节点（后继节点：中序遍历的后一个节点; 前驱节点：序遍历的前一个节点）
 * @createDate: 2022/1/25
 * @version: 1.0
 */
public class Code04_FindPostTreeNode {



    static class PNode {

        int val;
        PNode left;
        PNode right;
        PNode parent;

        public PNode(int val) {
            this.val = val;
        }
    }

    public static PNode findPostNode(PNode pNode) {
        if (pNode == null) {
            return pNode;
        }
        if (pNode.right != null) {
            // 当前节点有右节点，右子树的最左节点就是后继节点
            PNode cur = pNode.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        PNode cur = pNode;
        while (cur.parent != null) {
            PNode parent = cur.parent;
            if (parent.left == cur) {
                return parent;
            }
            cur = parent;
        }
        return null;
    }


    public static void main(String[] args) {
        PNode tree = new PNode(1);

        tree.left = new PNode(2);
        tree.right = new PNode(3);
        tree.left.parent = tree;
        tree.right.parent = tree;

        tree.left.left = new PNode(4);
        tree.left.right = new PNode(5);
        tree.left.left.parent = tree.left;
        tree.left.right.parent = tree.left;

        tree.right.left = new PNode(6);
        tree.right.right = new PNode(7);
        tree.right.left.parent = tree.right;
        tree.right.right.parent = tree.right;

        PNode postNode = findPostNode(tree.right);
        System.out.println(postNode.val);

    }

}
