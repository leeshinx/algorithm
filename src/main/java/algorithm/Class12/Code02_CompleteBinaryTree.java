package algorithm.Class12;

import entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Feng.Lee
 * 判断是否完全二叉树
 * @createDate: 2022/1/27
 * @version: 1.0
 */
public class Code02_CompleteBinaryTree {


    public static boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        boolean end = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (end && (node.right != null || node.left != null)) {
                return false;
            }
            if (node.right != null && node.left == null) {
                return false;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.right == null || node.left == null) {
                end = true;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
//        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);
        boolean isCBT = isCBT(tree);
        System.out.println(isCBT);

    }
}
