import com.company.MyArrayList;

import java.util.NoSuchElementException;

public class MyMinHeap<T extends Comparable<T>> {

    private MyArrayList<T> list;

    public MyMinHeap() {
        list = new MyArrayList<>();
    }

    public void insert(T item) {
        list.addElement(item); // Adding an element to the end of the list
        heapifyUp(); // We rebalance upwards
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = list.get(0); // We get the minimum element
        T last = list.getLast(); // Getting the last element
        list.set(0, last); // Replace the root element with the last one
        list.removeLast(); // Removing the last element
        heapifyDown(); // We rebalance downwards
        return min;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return list.get(0); // Returning the minimum element
    }


    private boolean isEmpty() {
        return list.isEmpty();
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < list.size();
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < list.size();
    }

    private T parent(int index) {
        return list.get(parentIndex(index));
    }

    private void swap(int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
