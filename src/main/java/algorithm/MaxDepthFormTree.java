package algorithm;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 树的最大深度
 * @createDate: 2021/12/22
 * @version: 1.0
 */
public class MaxDepthFormTree {

    public static void main(String[] args) {

    }


    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
