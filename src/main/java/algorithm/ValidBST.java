package algorithm;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * @createDate: 2021/12/23
 * @version: 1.0
 */
public class ValidBST {
    // 判断是否是平衡二叉搜索树
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidST(root).isST && isValiddBT(root).isBT;
    }


    public static class STInfo {
        private boolean isST;
        private int max;
        private int min;

        public STInfo(boolean isST, int max, int min) {
            this.isST = isST;
            this.max = max;
            this.min = min;
        }
    }

    // 判断是否搜素树 中序遍历有序  当前节点是搜索树， 当前节点值 大于左节点最大值，小于右节点最小值
    public STInfo isValidST(TreeNode root) {
        if (root == null) {
            return  null;
        }
        STInfo lInfo = isValidST(root.left);
        STInfo rInfo = isValidST(root.right);


        int max = root.val;
        int min = root.val;
        if (lInfo != null) {
            max = Math.max(max, lInfo.max);
            min = Math.min(min, lInfo.min);
        }
        if (rInfo != null) {
            max = Math.max(max, rInfo.max);
            min = Math.min(min, rInfo.min);
        }

        boolean isBT = true;
        if (lInfo != null && !lInfo.isST) {
            isBT = false;
        }
        if (rInfo != null && !rInfo.isST) {
            isBT = false;
        }

        boolean isLeftMax = lInfo == null ? true : root.val > lInfo.max;
        boolean isRightMin = rInfo == null ? true : root.val < rInfo.min;
        if (!isLeftMax || !isRightMin) {
            isBT = false;
        }

        return  new STInfo(isBT, max, min);
    }


    public static class BTInfo {

        private boolean isBT;
        private int height;

        public BTInfo(boolean isBT, int height) {
            this.isBT = isBT;
            this.height = height;
        }

    }

    // 判断是否平衡树 左子树 和 右子树 高度差不大于1
    public BTInfo isValiddBT(TreeNode root) {
        if (root == null) {
            return new BTInfo(true, 0);
        }

        BTInfo leftInfo = isValiddBT(root.left);
        BTInfo rightInfo = isValiddBT(root.right);
        boolean isBT = leftInfo.isBT && rightInfo.isBT && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        return new BTInfo(isBT, Math.max(leftInfo.height, rightInfo.height) + 1);
    }
}
