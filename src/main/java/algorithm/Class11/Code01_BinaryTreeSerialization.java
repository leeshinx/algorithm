package algorithm.Class11;

import entity.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化 和 反序列化
 * @author: Feng.Lee
 * @createDate: 2022/1/24
 * @version: 1.0
 */
public class Code01_BinaryTreeSerialization {

    // 可以利用前序or后序遍历进行序列化，不能使用中序遍历序列化
    public static LinkedList<String> serialize (TreeNode head) {
        LinkedList<String> ans = new LinkedList<>();
        preSerialize(head, ans);
        return ans;
    }

    private static void preSerialize(TreeNode cur, List<String> ans) {
        if (cur == null) {
            ans.add(null);
            return;
        }

        ans.add(String.valueOf(cur.val));
        preSerialize(cur.left, ans);
        preSerialize(cur.right, ans);
    }


    private static TreeNode deserialize(LinkedList<String> serial) {
        if (serial == null || serial.size() == 0) {
            return null;
        }
        return bulid(serial);
    }

    private static TreeNode bulid(LinkedList<String> list) {
        String val = list.pop();
        if (val == null) {
            return null;
        }
        TreeNode tree = new TreeNode(Integer.valueOf(val));
        tree.left = bulid(list);
        tree.right = bulid(list);
        return tree;
    }


    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);
        LinkedList serialize = serialize(tree);
        System.out.println(serialize);

        TreeNode treeNode = deserialize(serialize);
        System.out.println(treeNode);

        System.out.println(serialize(treeNode));
    }
}
