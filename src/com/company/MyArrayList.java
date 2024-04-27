package com.company;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {

    private T[] data;
    private int size;

    public MyArrayList() {
        this(10); // Default initial capacity
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        data = (T[]) new Object[initialCapacity];
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1); // Increase by 50%
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void addElement(T element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    public void set(int index, T item) {
        checkIndex(index);
        data[index] = item;
    }

    public void add(int index, T item) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = item;
        size++;
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void addLast(T item) {
        addElement(item);
    }

    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[0];
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[size - 1];
    }


    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null; // Help GC
    }


    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            data[0] = null; // Help GC
        } else {
            data[size - 1] = null; // Help GC
        }
        size--;
    }

    public void sort() {
        // You can use any sorting algorithm here, such as Arrays.sort()
        Arrays.sort(data, 0, size);
    }

    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public void clear() {
        Arrays.fill(data, 0, size, null); // Help GC
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}