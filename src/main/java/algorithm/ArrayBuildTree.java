package algorithm;

import entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Feng.Lee
 * 给前序遍历和中序遍历构建出树
 * @createDate: 2021/12/22
 * @version: 1.0
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ArrayBuildTree {

    public static void main(String[] args) {
        int[] perArr = {4,5,6,8,7,1,3};
        int[] inArr = {6,5,8,4,1,7,3};
        TreeNode tree = buildTree(perArr, inArr);
        TranversalBinaryTree.pre(tree);
        System.out.println();
        TranversalBinaryTree.in(tree);


    }


    public static TreeNode buildTree(int[] perArr, int[] inArr) {
        if (perArr == null || inArr == null || perArr.length != inArr.length) {
            return null;
        }
        Map<Integer, Integer> valIdxMap = new HashMap<>(inArr.length << 1);
        for (int i = 0; i < inArr.length; i++) {
            valIdxMap.put(inArr[i], i);
        }

        return doBuildTree(perArr, 0, perArr.length -1, inArr, 0, inArr.length -1, valIdxMap);
    }

    public static TreeNode doBuildTree(int[] perArr, int pl, int pr, int[] inArr, int il, int ir, Map<Integer, Integer> valIdxMap) {
        if (pl > pr || il > ir) {
            return null;
        }
        TreeNode tree = new TreeNode(perArr[pl]);
        if (pl == pr) {
            return tree;
        }
        int find = valIdxMap.get(perArr[pl]);

        tree.left = doBuildTree(perArr, pl + 1, pl + find - il, inArr, il, find -1, valIdxMap);
        tree.right = doBuildTree(perArr, pl + find - il + 1, pr, inArr, find + 1, ir, valIdxMap);
        return tree;
    }
}
