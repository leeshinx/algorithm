package algorithm;

import entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Feng.Lee
 * 返回树的每层的值 使用广度优先
 * @createDate: 2021/12/23
 * @version: 1.0
 */
public class LevelOrderTree {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        List<TreeNode> oldLevelNode = new ArrayList<>(1);
        oldLevelNode.add(root);
        while (!oldLevelNode.isEmpty()) {
            List<Integer> levelRes = new ArrayList<>(oldLevelNode.size());
            // 使用队列，先进后出，这样就不用每次都 new ArrayList, 可以减少空间复杂度
            List<TreeNode> levelNode = new ArrayList<>();
            for (TreeNode treeNode : oldLevelNode) {
                levelRes.add(treeNode.val);

                if (treeNode.left != null) {
                    levelNode.add(treeNode.left);
                }

                if (treeNode.right != null) {
                    levelNode.add(treeNode.right);
                }
            }
            oldLevelNode = levelNode;
            res.add(levelRes);
        }
        return res;
    }
}
