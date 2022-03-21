package algorithm.Class08;

/**
 * @author: Feng.Lee
 * 前缀树
 * @createDate: 2022/1/12
 * @version: 1.0
 */
public class Code01_TrieTree {


    public static class Node {
        // 通过，最后节点
        private int pass;
        private int end;
        // 只能用26个字母
        private Node[] nexts;
        // 当字符很多 key：字符编码
//        private Map<Integer, Node> nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }
    }

    public static class Trie {

        private Node root;

        public Trie() {
            this.root = new Node();
        }

        public void put(String word) {
            char[] charArray = word.toCharArray();
            Node point = root;
            point.pass++;
            for (char c : charArray) {
                int index = c - 'a';
                if (point.nexts[index] == null) {
                    point.nexts[index] = new Node();
                }
                point = point.nexts[index];
                point.pass++;
            }
            point.end++;
        }

        public boolean remove(String word) {
            if (!contains(word)) {
                return false;
            }
            char[] charArray = word.toCharArray();
            Node point = root;
            point.pass--;
            for (char c : charArray) {
                int index = c - 'a';
                if (--point.nexts[index].pass == 0) {
                    point.nexts[index] = null;
                    return true;
                }
                point = point.nexts[index];
            }
            point.end--;
            return true;
        }

        public boolean contains(String word) {
            char[] charArray = word.toCharArray();
            Node point = root;
            for (char c : charArray) {
                int index = c - 'a';
                if (point.nexts[index] == null) {
                    return false;
                }
                point = point.nexts[index];
            }
            return point.end >= 1;
        }

        public boolean prefixMatch(String word){
            char[] charArray = word.toCharArray();
            Node point = root;
            for (char c : charArray) {
                int index = c - 'a';
                if (point.nexts[index] == null) {
                    return false;
                }
                point = point.nexts[index];
            }
            return true;
        }
    }

    private static String str="abcdefghijklmnopqrstuvwxyz";

    // 对数器 - 随机数组
    public static String generateRandomArray(int maxSize) {
        int size = (int)(Math.random() * (maxSize + 1));
        char[] arr = new char[size];
        int maxValue = str.length();
        for (int i = 0; i < size; i++) {
            int index = (int)(Math.random() * maxValue);
            System.out.println(index);
            arr[i] = str.charAt(index);
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        int times = 1000;
        int maxLength = 20;
        Trie trie = new Trie();
        for (int i = 0; i < times; i++) {
            String word = generateRandomArray(maxLength);
            trie.put(word);
            if (!trie.contains(word)) {
                System.out.println("fail：" + word);
                System.out.println("--------fail-------");

            }
        }
        System.out.println("--------success-------");
    }

}
