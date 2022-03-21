package algorithm.Class11;

import entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Feng.Lee
 * @createDate: 2022/1/25
 * @version: 1.0
 */
public class Code03_MaxWidthBTree {

    public static int maxWidthBTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int mostWidth = 0;
        int levelWidth = 0;
        TreeNode curEnd = root;
        TreeNode lastEnd = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
                lastEnd = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                lastEnd = poll.right;
            }
            levelWidth++;
            if (curEnd == poll) {
                mostWidth = Math.max(mostWidth, levelWidth);
                levelWidth = 0;
                curEnd = lastEnd;
            }
        }
        return mostWidth;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(3);
        tree.right = new TreeNode(2);
        tree.left.left = new TreeNode(5);
        tree.left.right = new TreeNode(3);
        tree.right.right = new TreeNode(9);

        int i = maxWidthBTree(tree);
        System.out.println(i);
    }
}
