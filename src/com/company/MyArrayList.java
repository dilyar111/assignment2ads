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
        ensureCapacity(size + 1);// Ensure capacity is enough to accommodate new element
        array[size++] = item;// Add the item to the array and increment size
    }

    public void set(int index, T item) {
        checkIndex(index);// Check if index is valid
        array[index] = item;// Set the item at the specified index
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) {// Check if index is out of bounds
            throw new IndexOutOfBoundsException();//Throw exception if index is invalid
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);// Shift elements to make space for new element
        array[index] = item;// Insert the item at the specified index
        size++;// Increment size
    }

    public void addFirst(T item) {// Method to add an element to the beginning of the list
        add(0, item);// Call add method with index 0
    }

    public void addLast(T item) {// Method to add an element to the end of the list
        add(size, item);// Call add method with index size
    }

    public T get(int index) {// Method to get an element at a specified index
        checkIndex(index);// Check if index is valid
        return (T) array[index];// Return the element at the specified index
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();// Throw exception if list is empty
        }
        return (T) array[0];// Return the first element of the list
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) array[size - 1];
    }

    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;// Calculate number of elements to move
        if (numMoved > 0) {// Check if elements need to be moved
            System.arraycopy(array, index + 1, array, index, numMoved);// Shift elements to fill the gap
        }
        array[--size] = null;// Set the last element to null and decrement size
    }

    public void removeFirst() {// Method to remove the first element of the list
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        array[--size] = null;// Set the last element to null and decrement size
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        array[size - 1] = null;// Set the last element to null
        size--;
    }

    public void sort() {
        Arrays.sort(array, 0, size);// Sort the elements using Arrays.sort method
    }

    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {// Iterate through the elements of the list
            if (Objects.equals(array[i], object)) {// Check if the current element equals the specified object
                return i;// Return the index of the object
            }
        }
        return -1; // Return -1 if object is not found
    }

    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) { // Iterate through the elements of the list in reverse order
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return -1;// Return -1 if object is not found
    }

    public boolean exists(Object object) {// Method to check if a specified object exists in the list
        return indexOf(object) != -1;// Return true if object is found, false otherwise
    }

    public Object[] toArray() {// Method to convert the list to an array
        return Arrays.copyOf(array, size);// Return a copy of the array containing the elements of the list
    }

    public void clear() {
        Arrays.fill(array, 0, size, null);// Set all elements of the array to null
        size = 0;// Reset the size to zero
    }

    public int size() {// Method to get the size of the list
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length + (array.length >> 1); // Calculate new capacity
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
