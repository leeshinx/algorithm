package algorithm.Class13;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 236. 二叉树的最近公共祖先
 * @createDate: 2022/3/7
 * @version: 1.0
 */
public class Code02_LowestCommonAncestor {

    private static class Info {
        private boolean findP;
        private boolean findQ;
        private TreeNode ans;

        public Info(boolean findP, boolean findQ, TreeNode ans) {
            this.findP = findP;
            this.findQ = findQ;
            this.ans = ans;
        }
    }


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ans;
    }

    public static Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }

        Info left = process(root.left, p, q);
        Info right = process(root.right, p, q);

        boolean findP = left.findP || right.findP || root == p;
        boolean findQ = left.findQ || right.findQ || root == q;
        TreeNode ans = null;
        if (left.ans != null) {
            ans = left.ans;
        } else if (right.ans != null) {
            ans = right.ans;
        } else if (findP && findQ) {
            // 既找到p 又找到q，但是 ans还是null， 所有当前TreeNode就是ans
            ans = root;
        }

        return new Info(findP, findQ, ans);
    }
}
