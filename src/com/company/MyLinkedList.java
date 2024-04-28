package com.company;

import java.util.*;

public class MyLinkedList<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Adding an element to the end of the list
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Set element value by index
    public void set(int index, T item) {
        checkIndex(index);
        Node<T> current = getNode(index);
        current.data = item;
    }

    // Adding an element by index
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node<T> newNode = new Node<>(item);
            Node<T> current = getNode(index - 1);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    // Adding an element to the beginning of the list
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
        if (isEmpty()) {
            tail = newNode;
        }
        size++;
    }

    // Adding an element to the end of the list
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    // Getting an element by index
    public T get(int index) {
        checkIndex(index);
        Node<T> current = getNode(index);
        return current.data;
    }

    // Getting the first element of the list
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.data;
    }
    // Get the last element of the list
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }
    // Removing an element by index
    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            removeFirst();
        } else {
            Node<T> current = getNode(index - 1);
            current.next = current.next.next;
            if (index == size - 1) {
                tail = current;
            }
            size--;
        }
    }
    // Removing the first element of the list
    private void removeFirst() {
    }

    public void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }
    // Removing the last element of the list
    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) {
            head = tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
    }

    // Sort the list
    public void sort() {
        if (size <= 1) {
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            Node<T> current = head;
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable<T>) current.data).compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }

    // Finding the index of an element in the list
    public int indexOf(Object object) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.data, object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    // Find the last element index in the list
    public int lastIndexOf(Object object) {
        int index = -1;
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.data, object)) {
                index = i;
            }
            current = current.next;
        }
        return index;
    }

    // Checking if an element is in the list
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    // Convert list to array
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        Node<T> current = head;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }
    // Clear the list
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    // Get the list size
    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> getNode(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}



