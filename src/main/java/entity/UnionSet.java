package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author: Feng.Lee
 * 并查集
 * @createDate: 2022/3/10
 * @version: 1.0
 */
public class UnionSet<V> {

    // 数据 和  节点的关系表
    private HashMap<V, Node<V>> nodes;
    // 节点 和  它对应的父节点
    private HashMap<Node<V>, Node<V>> parents;
    // key:每集合的头结点  v:集合中有多少个子节点
    private HashMap<Node<V>, Integer> sizeMap;

    private static class Node<V> {
        private V value;

        public Node(V value) {
            this.value = value;
        }
    }

    // 初始化并查集  现在所有的节点都是单独的集合
    public UnionSet(List<V> values) {
        this.nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V cur : values) {
            Node<V> node = new Node<>(cur);
            nodes.put(cur, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    // 给你一个节点，直到往上的不能再往上，从而找到头节点
    public Node<V> findFather(Node<V> cur) {
        Stack<Node<V>> path =new Stack<>();
        while (cur != parents.get(cur)) {
            path.push(cur);
            cur = parents.get(cur);
        }
        while (!path.isEmpty()) {
            // 对原来的结构进行优化，使当前走过的链路都跟关联到头结点，使结构扁平化
            parents.put(path.pop(), cur);
        }
        return cur;
    }

    // 判断两个数据是否在同一个集合中
    public boolean isSameSet(V a, V b) {
        return findFather(this.nodes.get(a)) != findFather(this.nodes.get(b));
    }

    public void union(V a, V b) {
        Node<V> aHead = findFather(this.nodes.get(a));
        Node<V> bHead = findFather(this.nodes.get(b));
        // 不在同一个集合中
        if (aHead != bHead) {
            // a 和 b 集合中的节点数
            int aSetSize = this.sizeMap.get(aHead);
            int bSetSize = this.sizeMap.get(bHead);
            // 找到大集合中的头节点
            Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
            Node<V> small = big == aHead ? bHead : aHead;
            parents.put(small, big);
            sizeMap.put(big, aSetSize + bSetSize);
            sizeMap.remove(small);
        }
    }

}
