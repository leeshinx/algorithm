package algorithm.Class12;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 二叉树的最大距离
 * @createDate: 2022/2/16
 * @version: 1.0
 */
public class Code03_MaxDistanceTree {


    private static class Info {
        private int maxDistance;
        private int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    /**
     * 最大距离有三种可能性是：
     * 1. x左树最大距离
     * 2. x右树最大距离
     * 3. x左子树最大高度 + x右子树最大高度 + 1
     */
    public static int maxDistance(TreeNode head) {
        if (head == null) {
            return 1;
        }

        Info info = process(head);
        return info.maxDistance;
    }

    private static Info process(TreeNode cur) {
        if (cur == null) {
            return new Info(0, 0);
        }
        Info lInfo = process(cur.left);
        Info rInfo = process(cur.right);

        int height = Math.max(lInfo.height, rInfo.height) + 1;

        int maxDistance = Math.max(lInfo.maxDistance, Math.max(rInfo.maxDistance, lInfo.height + rInfo.height + 1));
        return new Info(maxDistance, height);
    }

}
