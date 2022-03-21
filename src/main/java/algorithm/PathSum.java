package algorithm;

import entity.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Feng.Lee
 * 树路径和有目标值的路径集合
 * @createDate: 2021/12/24
 * @version: 1.0
 */
public class PathSum {

    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        List<Integer> targetList = new LinkedList<>();
        doPathSum(root, res, targetSum, targetList);
        return res;
    }

    private void doPathSum(TreeNode root, List<List<Integer>> res, int targetSum, List<Integer> targetList) {
        if (root == null) {
            return;
        }
        targetList.add(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            res.add(depthCopy(targetList));
        }
        doPathSum(root.left, res, targetSum, targetList);
        doPathSum(root.right, res, targetSum, targetList);
        targetList.remove(targetList.size() -1);
    }

    public List<Integer> depthCopy(List<Integer> orgin) {
        List<Integer> res = new LinkedList<>();
        for (Integer integer : orgin) {
            res.add(integer);
        }
        return res;
    }

}
