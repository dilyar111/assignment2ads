import java.util.*; // Importing necessary Java utilities

public class MyMinHeap<T extends Comparable<T>> {

    private ArrayList<T> heap;

    public MyMinHeap() {
        heap = new ArrayList<>();
    }

    public void addElement(T data) { // Method to add an element to the heap
        heap.add(data); // Adding the element to the heap
        heapifyUp(heap.size() - 1); // Performing heapifyUp operation to maintain heap property
    }

    public T getMin() { // Method to get the minimum element from the heap
        if (isEmpty()) { // Checking if the heap is empty
            throw new NoSuchElementException("Heap is empty."); // Throwing exception if heap is empty
        }
        return heap.get(0); // Returning the minimum element from the heap
    }

    public T removeMin() { // Method to remove and return the minimum element from the heap
        if (isEmpty()) { // Checking if the heap is empty
            throw new NoSuchElementException("Heap is empty."); // Throwing exception if heap is empty
        }
        T min = heap.get(0); // Storing the minimum element
        T last = heap.remove(heap.size() - 1); // Removing the last element from the heap
        if (!isEmpty()) { // Checking if the heap is not empty after removal
            heap.set(0, last); // Replacing the root element with the last element
            heapifyDown(0); // Performing heapifyDown operation to maintain heap property
        }
        return min; // Returning the minimum element
    }

    public boolean isEmpty() { // Method to check if the heap is empty
        return heap.isEmpty(); // Returning true if the heap is empty, false otherwise
    }

    private void heapifyUp(int index) { // Method to restore heap property by moving an element up
        int parent = (index - 1) / 2; // Calculating the parent index of the current element
        while (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) { // Looping until the current element is at its correct position
            swap(index, parent); // Swapping the current element with its parent
            index = parent; // Updating index to its parent
            parent = (index - 1) / 2; // Updating parent index
        }
    }

    private void heapifyDown(int index) { // Method to restore heap property by moving an element down
        int left = 2 * index + 1; // Calculating the left child index
        int right = 2 * index + 2; // Calculating the right child index
        int smallest = index; // Initializing smallest as the current index
        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) { // Checking if left child exists and smaller than the current element
            smallest = left; // Updating smallest index
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) { // Checking if right child exists and smaller than the current element or left child
            smallest = right; // Updating smallest index
        }
        if (smallest != index) { // Checking if smallest index has changed
            swap(index, smallest); // Swapping the current element with the smallest child
            heapifyDown(smallest); // Recursively heapifying down the subtree rooted at the smallest child
        }
    }

    private void swap(int i, int j) { // Method to swap elements at given indices
        T temp = heap.get(i); // Storing element at index i in temp variable
        heap.set(i, heap.get(j)); // Setting element at index j to index i
        heap.set(j, temp); // Setting temp variable to index j
    }
}

