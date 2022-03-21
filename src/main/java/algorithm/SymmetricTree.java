package algorithm;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 是否对称树
 * @createDate: 2021/12/22
 * @version: 1.0
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class SymmetricTree {

    public static void main(String[] args) {

    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetricTree(root, root);
    }

    public static boolean isSymmetricTree(TreeNode a, TreeNode b){
        if (a == null ^ b == null) {
            return false;
        }
        if (a == null && b == null) {
            return true;
        }
        return a.val == b.val && isSymmetricTree(a.left, b.right) && isSymmetricTree(a.right, b.left);
    }

}
