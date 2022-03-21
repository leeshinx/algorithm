package algorithm.Class13;

import algorithm.Class12.Code02_CompleteBinaryTree;
import entity.TreeNode;
import util.GenerateRandom;

/**
 * @author: Feng.Lee
 * 递归判断是否完全二叉树
 * @createDate: 2022/3/7
 * @version: 1.0
 */
public class Code01_CompleteBinaryTree2 {

    private static class Info {
        private boolean isFull;
        private boolean isCBT;
        private int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }


    // 完全二叉树规则：1.子集都是是完全二叉树，2.子集高度差都不超过1
    public static boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }

        return process(head).isCBT;
    }

    private static Info process(TreeNode head) {
        if (head == null) {
            return new Info(true, true, 0);
        }

        Info lInfo = process(head.left);
        Info rInfo = process(head.right);
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        boolean isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
        boolean isCBT = false;
        if (isFull) {
            isCBT =true;
        } else if (lInfo.isCBT && rInfo.isFull && lInfo.height-rInfo.height == 1) {
            isCBT =true;
        } else if (lInfo.isFull && rInfo.isFull && lInfo.height-rInfo.height == 1) {
            isCBT =true;
        } else if (lInfo.isFull && rInfo.isCBT && lInfo.height==rInfo.height) {
            isCBT =true;
        }
        return new Info(isCBT, isFull, height);
    }


    public static void main(String[] args) {
        int maxHeight = 5;
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = GenerateRandom.generateRandomBT(maxHeight, maxValue);
            if (isCBT(head) != Code02_CompleteBinaryTree.isCBT(head)) {
                System.out.println("error!");
            }
        }
        System.out.println("finish!");

    }
}
