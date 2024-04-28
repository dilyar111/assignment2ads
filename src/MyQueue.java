import java.util.*;

public class MyQueue<T> implements MyList<T> {
    private LinkedList<T> queue;

    public MyQueue() {
        queue = new LinkedList<>();
    }

    @Override
    public void addElement(T data) {
        queue.offer(data);
    }


    public void add(int index, T item) {
        throw new UnsupportedOperationException("Queue does not support adding elements at a specific index.");
    }


    public void set(int index, T item) {
        throw new UnsupportedOperationException("Queue does not support setting elements at a specific index.");
    }


    public void addFirst(T item) {
        throw new UnsupportedOperationException("Queue does not support adding elements at the beginning.");
    }


    public void addLast(T item) {
        addElement(item);
    }


    public T get(int index) {
        throw new UnsupportedOperationException("Queue does not support random access.");
    }


    public T getFirst() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return queue.peek();
    }


    public T getLast() {
        throw new UnsupportedOperationException("Queue does not support accessing the last element.");
    }


    public void remove(int index) {
        throw new UnsupportedOperationException("Queue does not support removing elements at a specific index.");
    }


    public void removeFirst() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        queue.poll();
    }


    public void removeLast() {
        throw new UnsupportedOperationException("Queue does not support removing elements at the end.");
    }


    public void remove() {
        removeFirst();
    }

    public void sort() {
        throw new UnsupportedOperationException("Queue does not support sorting.");
    }

    public int lastIndexOf(Object object) {
        throw new UnsupportedOperationException("Queue does not support lastIndexOf operation.");
    }

    public boolean exists(Object object) {
        return queue.contains(object);
    }

    public Object[] toArray() {
        return queue.toArray();
    }

    public void clear() {
        queue.clear();
    }

    public int size() {
        return queue.size();
    }

    public Iterator<T> iterator() {
        return queue.iterator();
    }
}

