package algorithm;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 路径和是否有目标值
 * @createDate: 2021/12/24
 * @version: 1.0
 */
public class HasPathSum {

    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 必须遍历到叶子节点才能结束，不能提前结束
        if (root.left == null && root.right == null) {
            return targetSum - root.val == 0;
        }
        boolean lHaspath = hasPathSum(root.left, targetSum - root.val);
        boolean rHaspath = hasPathSum(root.right, targetSum - root.val);
        return lHaspath || rHaspath;
    }

}
