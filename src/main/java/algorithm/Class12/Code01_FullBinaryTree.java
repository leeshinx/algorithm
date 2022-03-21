package algorithm.Class12;

import entity.TreeNode;

/**
 * @author: Feng.Lee
 * 判断是否满二叉树
 *
 * 平衡二叉树：左子树和右子树高度差不大于1
 *
 *
 * @createDate: 2022/1/26
 * @version: 1.0
 */
public class Code01_FullBinaryTree {

    static class TreeInfo {
        int heigth;
        int size;

        public TreeInfo(int heigth, int size) {
            this.heigth = heigth;
            this.size = size;
        }
    }
    
    // 根据节点数=高度*2-1 判断是否满二叉树
    public static boolean isFullBinaryTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        TreeInfo treeInfo = process(head);
        boolean isFull = (1 << treeInfo.heigth) - 1 == treeInfo.size;
        return isFull;
    }
    
    public static TreeInfo process(TreeNode head) {
        if (head == null) {
            return new TreeInfo(0 ,0);
        }

        TreeInfo leftInfo = process(head.left);
        TreeInfo rightInfo = process(head.right);
        
        int heigth = Math.max(leftInfo.heigth, rightInfo.heigth) + 1;
        int size = leftInfo.size + rightInfo.size + 1;
        
        return new TreeInfo(heigth ,size);
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
//        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);
        boolean full = isFullBinaryTree(tree);
        System.out.println(full);

    }

}
