import java.util.*;

public class MyStack<T> implements MyList<T> {
    private final LinkedList<T> stack;

    public MyStack() {
        stack = new LinkedList<>();
    }

    @Override
    public void addElement(T data) {
        stack.push(data);
    }


    public void add(int index, T item) {
        throw new UnsupportedOperationException("Stack does not support adding elements at a specific index.");
    }


    public void set(int index, T item) {
        throw new UnsupportedOperationException("Stack does not support setting elements at a specific index.");
    }


    public void addFirst(T item) {
        throw new UnsupportedOperationException("Stack does not support adding elements at the beginning.");
    }


    public void addLast(T item) {
        throw new UnsupportedOperationException("Stack does not support adding elements at the end.");
    }


    public T get(int index) {
        throw new UnsupportedOperationException("Stack does not support random access.");
    }

    public T getFirst() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return stack.peek();
    }


    public T getLast() {
        throw new UnsupportedOperationException("Stack does not support accessing the last element.");
    }

    public void remove(int index) {
        throw new UnsupportedOperationException("Stack does not support removing elements at a specific index.");
    }

    public void removeFirst() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        stack.pop();
    }

    public void removeLast() {
        throw new UnsupportedOperationException("Stack does not support removing elements at the end.");
    }

    public void remove() {
        removeFirst();
    }

    public void sort() {
        throw new UnsupportedOperationException("Stack does not support sorting.");
    }

    public int lastIndexOf(Object object) {
        throw new UnsupportedOperationException("Stack does not support lastIndexOf operation.");
    }

    public boolean exists(Object object) {
        return stack.contains(object);
    }

    public Object[] toArray() {
        return stack.toArray();
    }

    public void clear() {
        stack.clear();
    }

    public int size() {
        return stack.size();
    }


    public Iterator<T> iterator() {
        return stack.iterator();
    }
}

