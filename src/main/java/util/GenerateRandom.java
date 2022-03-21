package util;

import entity.TreeNode;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Feng.Lee
 * @createDate: 2021/12/30
 * @version: 1.0
 */
public class GenerateRandom {

    // 对数器 - 随机数组
//    public static int[] generateRandomArray(int maxSize, int maxValue) {
//        int size = (int)(Math.random() * (maxSize + 1));
//        int[] arr = new int[size];
//        for (int i = 0; i < size; i++) {
//            arr[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * maxValue);
//        }
//        return arr;
//    }

    // 对数器 - 随机数组
    public static int[] generateRandomArray(int maxLength, int maxRange) {
        int length = new Random().nextInt(maxLength + 1);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Random().nextInt(maxRange + 1) - new Random().nextInt(maxRange);
        }
        return arr;
    }

    // 对数器 - 随机二叉树
    public static TreeNode generateRandomBT(int maxHeight, int maxValue) {
        int height = new Random().nextInt(maxHeight + 1);
        return generate(height, maxValue);
    }

    private static TreeNode generate(int height, int maxValue) {
        if (height <= 0) {
            return null;
        }
        int value = new Random().nextInt(maxValue + 1);
        TreeNode tree = new TreeNode(value);
        tree.left = generate(height - 1, maxValue);
        tree.right = generate(height - 1, maxValue);
        return tree;
    }

    public static void main(String[] args) {
        int times = 1000;
        int maxLength = 20;
        int maxRange = 20;
        for (int i = 0; i < times; i++) {
            int[] array = generateRandomArray(maxLength, maxRange);
            int[] copy = Arrays.copyOf(array, array.length);
//            mergeSort(array);
            System.out.println("排序：" + Arrays.toString(array));
            Arrays.sort(copy);
            System.out.println("比对：" + Arrays.toString(array));
            if (!Arrays.equals(array, copy)) {
                System.out.println("--------fail-------");
                break;
            }
        }
        System.out.println("--------success-------");
    }


}
