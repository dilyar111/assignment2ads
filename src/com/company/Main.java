package com.company;

import com.company.MyLinkedList;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        System.out.println("Value of index 3: " + myLinkedList.get(3));
        System.out.println("Size of the list: " + myLinkedList.size());
        System.out.println("Value of index 0 before removal: " + myLinkedList.get(0));
        myLinkedList.remove(0);
        System.out.println("Value of index 0 after removal: " + myLinkedList.get(0));
        System.out.println("Size of the list: " + myLinkedList.size());
        System.out.println("Value of index 2 before removal: " + myLinkedList.get(2));
        myLinkedList.remove(2);
        System.out.println("Value of index 2 after removal: " + myLinkedList.get(2));
        System.out.println("Size of the list: " + myLinkedList.size());
    }
}
