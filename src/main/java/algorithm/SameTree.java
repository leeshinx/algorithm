package algorithm;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 两个树相同
 * @createDate: 2021/12/22
 * @version: 1.0
 */
public class SameTree {



    public static void main(String[] args) {

    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q ==null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
