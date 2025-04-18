import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Priority Queue (Minimum)
 * @param <T>
 * @author Joseph Hargus
 */
public class Heap <T extends Number & Comparable<T>> {
    private static class Node<T extends Number & Comparable<T>> {
        int id;
        T key;

        Node(int id, T key){
            this.id = id;
            this.key = key;
        }
    }

    private List<Node<T>> heap;
    private Map<Integer, Integer> idToIndex;

    /**
     * Constructor
     * @param keys - element keys
     * @param n - number of element keys
     */
    public Heap(T[] keys, int n){
        heap = new ArrayList<>();
        idToIndex = new HashMap<>();
        for(int i = 0; i < n; i++){
            Node<T> node = new Node<>(i, keys[i]);
            heap.add(node);
            idToIndex.put(node.id, i);
        }
        buildHeap();
    }

    private void buildHeap(){
        for(int i = heap.size() / 2 - 1; i >= 0; i--){
            heapifyDown(i);
        }
    }

    private void heapifyDown(int index){
        int size = heap.size();
        while (true) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && heap.get(left).key.compareTo(heap.get(smallest).key) < 0){
                smallest = left;
            }
            if (right < size && heap.get(right).key.compareTo(heap.get(smallest).key) < 0){
                smallest = right;
            }
            if (smallest != index){
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void heapifyUp(int index){
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).key.compareTo(heap.get(parent).key) < 0){
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int index1, int index2){
        Node<T> temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
        idToIndex.put(heap.get(index1).id, index1);
        idToIndex.put(heap.get(index2).id, index2);
    }

    /**
     * returns true if the element whose id is id is in the heap
     * @param id
     * @return
     */
    public boolean in_heap(int id){
        return idToIndex.containsKey(id);
    }

    /**
     * returns the minimum key of the heap
     * @return null if heap is empty
     */
    public T min_key(){
        if (heap.isEmpty()) return null;
        return heap.get(0).key;
    }

    /**
     * returns the id of the element with minimum key in the heap
     * @return -1 if heap is empty
     */
    public int min_id(){
        if (heap.isEmpty()) return -1;
        return heap.get(0).id;
    }

    /**
     * returns the key of the element whose id is id in the heap
     * @param id
     * @return null if no element matches the given id
     */
    public T key(int id){
        Integer index = idToIndex.get(id);
        if (index == null) return null;
        return heap.get(index).key;
    }

    /**
     * deletes the element with minimum key from the heap
     */
    public void delete_min(){
        if (heap.isEmpty()) return;
        int last = heap.size() - 1;
        swap(0, last);
        Node<T> removed = heap.remove(last);
        idToIndex.remove(removed.id);
        heapifyDown(0);
    }

    /**
     * sets the key of the element whose id is id to new_key if
     * its current key is greater than new_key
     * @param id
     * @param new_key
     * @return true if successful, false otherwise
     */
    public boolean decrease_key(int id, T new_key){
        Integer index = idToIndex.get(id);
        if (index == null) return false;
        Node<T> node = heap.get(index);
        if (node.key.compareTo(new_key) <= 0) return false;
        node.key = new_key;
        heapifyUp(index);
        return true;
    }
}
