package com.company;

import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {

    private Node head;
    private Node tail;
    private int size;

    private static class Node<T> {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private boolean isEmpty() {
        return head == null;
    }

    private boolean isFirstIndex(int index) {
        return index == 0;
    }

    private boolean isLastIndex(int index) {
        return index == size - 1;
    }



    @Override
    public void addElement(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        }
        else {
            Node currentNode = head;
            while (currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
    }
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = item;
    }
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            addLast(item);
        } else if (isFirstIndex(index)) {
            addFirst(item);
        } else if (isLastIndex(index)) {
            addLast(item);
        } else {
            Node<T> newNode = new Node<>(item);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }


    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) { // Handle empty list case
            head = tail = newNode;
        } else {
            tail.next = newNode; // Update tail's next pointer
            tail = newNode; // Update tail reference
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (T) current.data;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) head.data;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (isFirstIndex(index)) {
            removeFirst();
        } else if (isLastIndex(index)) {
            removeLast();
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            size--;
        }
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) { // Handle single-element list
            head = tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
    }

    public void sort() {
        if (size <= 1) {
            return; // Nothing to sort for 0 or 1 elements
        }
        for (int i = 0; i < size - 1; i++) {
            Node current = head;
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable<T>) current.data).compareTo((T) current.next.data) > 0) {
                    // Swap data using a temporary variable
                    T temp = (T) current.data;
                    current.data = (T) current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }
    public int indexOf(Object object) {
        if (object == null) {
            // Handle null case separately
            Node current = head;
            for (int i = 0; i < size; i++) {
                if (current.data == null) {
                    return i;
                }
                current = current.next;
            }
            return -1;
        }
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        int index = -1;
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object)) {
                index = i;
            }
            current = current.next;
        }
        return index;
    }

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        Node current = head;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }
}



