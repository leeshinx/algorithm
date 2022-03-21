package algorithm.Class11;

import entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Feng.Lee
 * 多叉树序列化成二叉树 并且可以反序列化
 * @createDate: 2022/1/25
 * @version: 1.0
 */
public class Code02_NTreeSerialization {

    static class NTree {
        int val;
        List<NTree> childs;

        public NTree(int val) {
            this.val = val;
        }

        public NTree(int val, List<NTree> childs) {
            this.val = val;
            this.childs = childs;
        }
    }


//    private static TreeNode encode(NTree head) {
//        if (head == null) {
//            return null;
//        }
//        return  doEncode(head);
//    }
//
//    private static TreeNode doEncode(NTree head) {
//        if (head == null) {
//            return null;
//        }
//        TreeNode tree = new TreeNode(head.val);
//        List<NTree> childs = head.childs;
//        if (childs == null || childs.size() == 0) {
//            return tree;
//        }
//        tree.left = doEncode(childs.get(0));
//        TreeNode cur = tree.left;
//        for (int i = 1; i < childs.size(); i++) {
//            TreeNode child = doEncode(childs.get(i));
//            cur.right = child;
//            cur = child;
//        }
//        return tree;
//    }

    private static TreeNode encode(NTree head) {
        if (head == null) {
            return null;
        }
        TreeNode root = new TreeNode(head.val);
        root.left = doEncode(head.childs);
        return  root;
    }

    private static TreeNode doEncode(List<NTree> childs) {
        if (childs == null || childs.size() == 0) {
            return null;
        }
        TreeNode head = null;
        TreeNode cur = null;
        for (int i = 0; i < childs.size(); i++) {
            TreeNode tree = new TreeNode(childs.get(i).val);
            if (head == null) {
                head = tree;
            } else {
                cur.right = tree;
            }
            cur = tree;
            cur.left = doEncode(childs.get(i).childs);
        }
        return head;
    }


//    private static NTree decode(TreeNode head) {
//        if (head == null) {
//            return null;
//        }
//        return doDecode(head);
//    }
//
//    private static NTree doDecode(TreeNode head) {
//        if (head == null) {
//            return null;
//        }
//        NTree nTree = new NTree(head.val);
//        if (head.left == null) {
//            return nTree;
//        }
//        List<NTree> childs = new ArrayList<>();
//        childs.add(doDecode(head.left));
//        TreeNode cur = head.left.right;
//        while (cur != null) {
//            childs.add(doDecode(cur));
//            cur = cur.right;
//        }
//        nTree.childs = childs;
//        return nTree;
//    }

    private static NTree decode(TreeNode head) {
        if (head == null) {
            return null;
        }
        return new NTree(head.val, doDecode(head.left));
    }

    private static List<NTree> doDecode(TreeNode head) {
        if (head == null) {
            return null;
        }
        List<NTree> childs = new ArrayList<>();
        TreeNode cur = head;
        while (cur != null) {
            childs.add(new NTree(cur.val, doDecode(cur.left)));
            cur = cur.right;
        }
        return childs;
    }

    public static void main(String[] args) {
        NTree nTree = new NTree(1);
        List<NTree> childs = new ArrayList<>();
        NTree child2 = new NTree(2);
        NTree child3 = new NTree(3);
        NTree child4 = new NTree(4);
        childs.add(child2);
        childs.add(child3);
        childs.add(child4);
        nTree.childs = childs;

        childs = new ArrayList<>();
        NTree child5 = new NTree(5);
        NTree child6 = new NTree(6);
        childs.add(child5);
        childs.add(child6);
        child2.childs = childs;

        childs = new ArrayList<>();
        NTree child7 = new NTree(7);
        childs.add(child7);
        child3.childs = childs;

        TreeNode treeNode = encode(nTree);

        NTree deserialize = decode(treeNode);
        System.out.println(deserialize);
    }

}
