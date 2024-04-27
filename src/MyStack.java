import com.company.MyArrayList;

import java.util.NoSuchElementException;

public class MyStack<T> {

    private MyArrayList<T> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    public void push(T item) {
        list.addLast(item); // Adding an element to the end of the list
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T item = list.getLast(); // Getting the last element
        list.removeLast(); //Removing the last element from the list
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return list.getLast(); // Returning the last element without removing it
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
