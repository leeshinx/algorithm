package algorithm;

import entity.TreeNode;

import java.util.Stack;

/**
 * @author: Feng.Lee
 * 树的遍历
 * @createDate: 2021/12/22
 * @version: 1.0
 */
public class TranversalBinaryTree {
    

    public static void f(TreeNode head) {
        if (head == null) {
            return;
        }
        // 1先序遍历-头->左->右
        f(head.left);
        // 2中序遍历-左->头->右
        f(head.right);
        // 3后序遍历-左->右->头
    }

    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        pre(head.left);
        pre(head.right);
    }

    public static void in(TreeNode head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.val + " ");
        in(head.right);
    }

    public static void pos(TreeNode head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.val + " ");
        Stack stack = new Stack();

    }

    // ------------------非递归----------------------------

    public static void pre1(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.val  + " ");
            if (pop.right != null) {
                stack.add(pop.right);
            }
            if (pop.left != null) {
                stack.add(pop.left);
            }
        }
    }

    public static void in1(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                // 先把当前树的 所有左侧节点压栈
                stack.add(cur);
                cur = cur.left;
            } else {
                // 当前树的 所有左侧节点已全部压栈，出栈
                cur = stack.pop();
                System.out.print(cur.val  + " ");
                // cur指向把右子树， 把右子树所有左侧节点压栈
                cur = cur.right;
            }
        }
    }

    public static void pos1(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> posStack = new Stack<>();
        stack.add(head);
        // 把 前 右 左 反过来就是 左 右 前
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            posStack.add(pop);
            if (pop.left != null) {
                stack.add(pop.left);
            }
            if (pop.right != null) {
                stack.add(pop.right);
            }
        }
        while (!posStack.isEmpty()) {
            System.out.print(posStack.pop().val  + " ");
        }
    }


    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);

        pre(tree);
        System.out.println();
        pre1(tree);
        System.out.println();
        System.out.println("==================");

        pos(tree);
        System.out.println();
        pos1(tree);
        System.out.println();
        System.out.println("==================");
        in(tree);
        System.out.println();
        in1(tree);
    }
}
