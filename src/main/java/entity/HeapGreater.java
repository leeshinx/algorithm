package entity;

import java.util.*;

/**
 * @author: Feng.Lee
 * 加强堆，有反向索引 未测试
 * 07课加强堆 改写题得奖规则未写
 * @createDate: 2022/1/11
 * @version: 1.0
 */
public class HeapGreater<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator comparator;

    public HeapGreater(Comparator comparato) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparato;
    }

    public int getSize(){
        return heapSize;
    }

    public boolean contail(T obj) {
        return  indexMap.containsKey(obj);
    }

    public void push(T obj){
        heap.add(heapSize, obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop(){
        T res = heap.get(0);
        swap(0, heapSize -1);
        indexMap.remove(res);
        heap.remove(--heapSize);
        heapify(0);
        return res;
    }

    public void remove (T obj) {
        T replace = heap.get(heapSize -1);
        int idx = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (idx != heapSize) {
            heap.set(idx, replace);
            indexMap.put(replace, idx);
            resign(idx);
        }
    }

    public List<T> getAllElements() {
        List<T> res = new ArrayList<>(heapSize);
        for (T t : heap) {
            res.add(t);
        }
        return res;
    }

    private void resign(int index) {
        heapInsert(index);
        heapify(index);
    }

    public void heapInsert(int index) {
        int parent = (index - 1) >> 1;
        while (parent <= 0 && comparator.compare(heap.get(parent), heap.get(index)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) >> 1;
        }
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int rigth = left + 1;
            int maxIdx = rigth < heapSize && comparator.compare(heap.get(left), heap.get(rigth)) < 0 ? rigth : left;
            int largeIdx = comparator.compare(heap.get(index), heap.get(maxIdx)) < 0 ? maxIdx : index;
            if (largeIdx == index) {
                break;
            }
            swap(largeIdx, index);
            index = largeIdx;
            left = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        T obj1 = heap.get(i);
        T obj2 = heap.get(j);
        heap.set(i, obj2);
        heap.set(j, obj1);
        indexMap.put(obj1, j);
        indexMap.put(obj2, i);
    }
}
