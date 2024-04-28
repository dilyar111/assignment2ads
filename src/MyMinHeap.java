import java.util.*;

public class MyMinHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public MyMinHeap() {
        heap = new ArrayList<>();
    }

    public void addElement(T data) {
        heap.add(data);
        heapifyUp(heap.size() - 1);
    }

    public T getMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap.get(0);
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;
        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
