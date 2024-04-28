package com.company;

import java.util.*;

public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T item) {
        ensureCapacity(size + 1);
        array[size++] = item;
    }

    public void set(int index, T item) {
        checkIndex(index);
        array[index] = item;
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void addLast(T item) {
        add(size, item);
    }

    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) array[0];
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) array[size - 1];
    }

    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
    }

    public void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        array[--size] = null;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        array[size - 1] = null;
        size--;
    }

    public void sort() {
        Arrays.sort(array, 0, size);
    }

    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return -1;
    }

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length + (array.length >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
