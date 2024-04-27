package com.company;

public class Main {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();

        myLinkedList.addElement(1);
        myLinkedList.addElement(2.5);
        myLinkedList.addElement('A');


        System.out.println("Index of an index 3:" + myLinkedList.get(5));
        myLinkedList.remove(5);
        System.out.println("Index of an index 3:" + myLinkedList.get(1));
    }
}