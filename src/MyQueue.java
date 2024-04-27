import com.company.MyArrayList;

import java.util.NoSuchElementException;

public class MyQueue<T> {

    private MyArrayList<T> list;

    public MyQueue() {
        list = new MyArrayList<>();
    }

    public void enqueue(T item) {
        list.addLast(item); // Adding an element to the end of the list
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = list.getFirst(); // Getting the first element
        list.removeFirst(); // Removing the first element from the list
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.getFirst(); // Returning the first element without removing it
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
