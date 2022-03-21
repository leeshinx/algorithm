package algorithm.Class12;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 二叉树的子树中最多的搜索二叉树节点数
 * @createDate: 2022/2/16
 * @version: 1.0
 */
public class Code04_MaxSubBSTSize {

    private static class Info {
        private int maxSubBSTSize;
        private int allSize;
        private int min;
        private int max;

        public Info(int maxSubBSTSize, int allSize, int min, int max) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.allSize = allSize;
            this.min = min;
            this.max = max;
        }
    }


    /**
     * ---------------未验证
     * 有三种可能性是：
     * 1. x左树最大搜索二叉树节点数 (与x节点无关)
     * 2. x右树最大搜索二叉树节点数 (与x节点无关)
     * 3. x搜索二叉树节点数 + x搜索二叉树节点数 + 1
     */
    public static int maxSubBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Info info = process(head);
        return info.maxSubBSTSize;
    }

    private static Info process(TreeNode cur) {
        if (cur == null) {
            return new Info(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info lInfo = process(cur.left);
        Info rInfo = process(cur.right);

        int allSize = lInfo.allSize + rInfo.allSize + 1;
        int min = Math.min(Math.min(lInfo.min, rInfo.min), cur.val);
        int max = Math.max(Math.max(lInfo.max, rInfo.max), cur.val);
        //  allSize == maxSubBSTSize 则是搜索二叉树
        boolean isLBST = lInfo.allSize == lInfo.maxSubBSTSize;
        boolean isRBST = rInfo.allSize == rInfo.maxSubBSTSize;
        // 当前是搜索二叉树
        boolean isBST = isLBST && isRBST && lInfo.max < cur.val && lInfo.min > cur.val;
        int maxSubBSTSize = isBST ? lInfo.maxSubBSTSize + rInfo.maxSubBSTSize + 1 : Math.max(lInfo.maxSubBSTSize, rInfo.maxSubBSTSize);

        return new Info(maxSubBSTSize, allSize, min, max);
    }
}
