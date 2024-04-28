
 * MyLinkedList represents a generic singly linked list data structure.
 * It provides methods for adding, removing, and accessing elements,
 * as well as additional operations like sorting and checking for element existence.
 *
 * @param <T> the type of elements stored in the linked list
 *
 * Time Complexity:
 * - Accessing an element by index: O(n)
 * - Adding an element at the end: O(1)
 * - Adding an element at the beginning: O(1)
 * - Adding an element at a specific index: O(n)
 * - Removing an element from the end: O(n)
 * - Removing an element from the beginning: O(1)
 * - Removing an element from a specific index: O(n)
 * - Checking for element existence: O(n)
 * - Sorting the list: O(n^2)
 
public class MyLinkedList<T> {

    // Nested Node class to represent elements in the linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        // Constructor to initialize the node with data
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param item the item to be added
     */
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = tail = newNode; // If list is empty, set head and tail to the new node
        } else {
            tail.next = newNode; // Append the new node to the end of the list
            tail = newNode; // Update tail to the new node
        }
        size++;
    }

    /**
     * Sets the value of the element at the specified index.
     *
     * @param index the index of the element to be updated
     * @param item  the new value of the element
     */
    public void set(int index, T item) {
        checkIndex(index); // Check if index is valid
        Node<T> current = getNode(index); // Get the node at the specified index
        current.data = item; // Update the data of the node
    }

    /**
     * Adds an element at the specified index.
     *
     * @param index the index at which the element is to be added
     * @param item  the element to be added
     */
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

    /**
     * Adds an element to the beginning of the list.
     *
     * @param item the item to be added
     */
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
        if (isEmpty()) {
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param item the item to be added
     */
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    public T get(int index) {
        checkIndex(index);
        Node<T> current = getNode(index);
        return current.data;
    }

    /**
     * Retrieves the first element of the list.
     *
     * @return the first element of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    /**
     * Retrieves the last element of the list.
     *
     * @return the last element of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove
     */
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

    /**
     * Removes the first occurrence of the specified element from the list.
     */
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

    /**
     * Removes the last element of the list.
     */
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

    /**
     * Sorts the list in ascending order.
     */
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

    /**
     * Finds the index of the first occurrence of the specified element in the list.
     *
     * @param object the element to search for
     * @return the index of the first occurrence of the element, or -1 if not found
     */
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

    /**
     * Finds the index of the last occurrence of the specified element in the list.
     *
     * @param object the element to search for
     * @return the index of the last occurrence of the element, or -1 if not found
     */
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

    /**
     * Checks if the specified element exists in the list.
     *
     * @param object the element to check for
     * @return true if the element exists in the list, false otherwise
     */
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * Converts the list to an array.
     *
     * @return an array containing all the elements in the list
     */
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

    /**
     * Clears the list, removing all elements.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the size of the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the specified index is valid.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the node at the specified index.
     *
     * @param index the index of the node to retrieve
     * @return the node at the specified index
     */
    private Node<T> getNode(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}





 * MyArrayList represents a generic dynamic array data structure.
 * It provides methods for adding, removing, and accessing elements,
 * as well as additional operations like sorting and checking for element existence.
 *
 * @param <T> the type of elements stored in the array list
 *
 * Time Complexity:
 * - Accessing an element by index: O(1)
 * - Adding an element at the end: O(1)
 * - Adding an element at the beginning: O(n)
 * - Adding an element at a specific index: O(n)
 * - Removing an element from the end: O(1)
 * - Removing an element from the beginning: O(n)
 * - Removing an element from a specific index: O(n)
 * - Checking for element existence: O(n)
 * - Sorting the list: O(n log n)

public class MyArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size;

    /**
     * Constructs an empty array list with the default initial capacity.
     */
    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Adds an element to the end of the array list.
     *
     * @param item the item to be added
     */
    public void add(T item) {
        ensureCapacity(size + 1); // Ensure capacity is enough to accommodate new element
        array[size++] = item; // Add the item to the array and increment size
    }

    /**
     * Sets the value of the element at the specified index.
     *
     * @param index the index of the element to be updated
     * @param item  the new value of the element
     */
    public void set(int index, T item) {
        checkIndex(index); // Check if index is valid
        array[index] = item; // Set the item at the specified index
    }

    /**
     * Adds an element at the specified index.
     *
     * @param index the index at which the element is to be added
     * @param item  the element to be added
     */
    public void add(int index, T item) {
        if (index < 0 || index > size) { // Check if index is out of bounds
            throw new IndexOutOfBoundsException(); // Throw exception if index is invalid
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index); // Shift elements to make space for new element
        array[index] = item; // Insert the item at the specified index
        size++; // Increment size
    }

    /**
     * Adds an element to the beginning of the array list.
     *
     * @param item the item to be added
     */
    public void addFirst(T item) {
        add(0, item); // Call add method with index 0
    }

    /**
     * Adds an element to the end of the array list.
     *
     * @param item the item to be added
     */
    public void addLast(T item) {
        add(size, item); // Call add method with index size
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    /**
     * Retrieves the first element of the array list.
     *
     * @return the first element of the array list
     * @throws NoSuchElementException if the array list is empty
     */
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) array[0];
    }

    /**
     * Retrieves the last element of the array list.
     *
     * @return the last element of the array list
     * @throws NoSuchElementException if the array list is empty
     */
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) array[size - 1];
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove
     */
    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1; // Calculate number of elements to move
        if (numMoved > 0) { // Check if elements need to be moved
            System.arraycopy(array, index + 1, array, index, numMoved); // Shift elements to fill the gap
        }
        array[--size] = null; // Set the last element to null and decrement size
    }

    /**
     * Removes the first element of the array list.
     */
    public void removeFirst() {
        remove(0); // Call remove method with index 0
    }

    /**
     * Removes the last element of the array list.
     */
    public void removeLast() {
        remove(size - 1); // Call remove method with index size - 1
    }

    /**
     * Sorts the array list in ascending order.
     */
    public void sort() {
        Arrays.sort(array, 0, size); // Sort the elements using Arrays.sort method
    }

    /**
     * Finds the index of the first occurrence of the specified element in the array list.
     *
     * @param object the element to search for
     * @return the index of the first occurrence of the element, or -1 if not found
     */
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) { // Iterate through the elements of the array list
            if (Objects.equals(array[i], object)) { // Check if the current element equals the specified object
                return i; // Return the index of the object
            }
        }
        return -1; // Return -1 if object is not found
    }

    /**
     * Finds the index of the last occurrence of the specified element in the array list.
     *
     * @param object the element to search for
     * @return the index of the last occurrence of the element, or -1 if not found
     */
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) { // Iterate through the elements of the array list in reverse order
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return -1; // Return -1 if object is not found
    }

    /**
     * Checks if the specified element exists in the array list.
     *
     * @param object the element to check for
     * @return true if the element exists in the array list, false otherwise
     */
    public boolean exists(Object object) {
        return indexOf(object) != -1; // Return true if object is found, false otherwise
    }

    /**
     * Converts the array list to an array.
     *
     * @return an array containing all the elements in the array list
     */
    public Object[] toArray() {
        return Arrays.copyOf(array, size); // Return a copy of the array containing the elements of the array list
    }

    /**
     * Clears the array list, removing all elements.
     */
    public void clear() {
        Arrays.fill(array, 0, size, null); // Set all elements of the array to null
        size = 0; // Reset the size to zero
    }

    /**
     * Returns the size of the array list.
     *
     * @return the number of elements in the array list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the array list is empty.
     *
     * @return true if the array list is empty, false otherwise
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the specified index is valid.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Ensures that the array list has enough capacity to accommodate the specified minimum capacity.
     *
     * @param minCapacity the minimum capacity required
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length + (array.length >> 1); // Calculate new capacity
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }
}






 * MyStack represents a generic stack data structure.
 * It implements the MyList interface and provides stack-specific operations.
 *
 * @param <T> the type of elements stored in the stack
 *
 * Time Complexity:
 * - Adding an element to the stack: O(1)
 * - Removing an element from the stack: O(1)
 * - Checking for element existence: O(n)
 * - Converting the stack to an array: O(n)

public class MyStack<T> implements MyList<T> {
    private final LinkedList<T> stack;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        stack = new LinkedList<>();
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param data the element to be added
     */
    @Override
    public void addElement(T data) {
        stack.push(data);
    }

    /**
     * Unsupported operation for stack.
     *
     * @param index the index at which the element is to be added
     * @param item  the element to be added
     */
   
    public void add(int index, T item) {
        throw new UnsupportedOperationException("Stack does not support adding elements at a specific index.");
    }

    /**
     * Unsupported operation for stack.
     *
     * @param index the index of the element to update
     * @param item  the new value of the element
     */

    public void set(int index, T item) {
        throw new UnsupportedOperationException("Stack does not support setting elements at a specific index.");
    }

    /**
     * Unsupported operation for stack.
     *
     * @param item the element to be added at the beginning
     */

    public void addFirst(T item) {
        throw new UnsupportedOperationException("Stack does not support adding elements at the beginning.");
    }

    /**
     * Unsupported operation for stack.
     *
     * @param item the element to be added at the end
     */

    public void addLast(T item) {
        throw new UnsupportedOperationException("Stack does not support adding elements at the end.");
    }

    /**
     * Unsupported operation for stack.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    
    public T get(int index) {
        throw new UnsupportedOperationException("Stack does not support random access.");
    }

    /**
     * Retrieves the top element of the stack.
     *
     * @return the top element of the stack
     * @throws NoSuchElementException if the stack is empty
     */

    public T getFirst() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return stack.peek();
    }

    /**
     * Unsupported operation for stack.
     *
     * @return UnsupportedOperationException always thrown
     */

    public T getLast() {
        throw new UnsupportedOperationException("Stack does not support accessing the last element.");
    }

    /**
     * Unsupported operation for stack.
     *
     * @param index the index of the element to remove
     */

    public void remove(int index) {
        throw new UnsupportedOperationException("Stack does not support removing elements at a specific index.");
    }

    /**
     * Removes the top element of the stack.
     *
     * @throws NoSuchElementException if the stack is empty
     */
  
    public void removeFirst() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        stack.pop();
    }

    /**
     * Unsupported operation for stack.
     */
   
    public void removeLast() {
        throw new UnsupportedOperationException("Stack does not support removing elements at the end.");
    }

    /**
     * Removes the top element of the stack.
     * Alias for removeFirst().
     */
    public void remove() {
        removeFirst();
    }

    /**
     * Unsupported operation for stack.
     */

    public void sort() {
        throw new UnsupportedOperationException("Stack does not support sorting.");
    }

    /**
     * Unsupported operation for stack.
     */
 
    public int lastIndexOf(Object object) {
        throw new UnsupportedOperationException("Stack does not support lastIndexOf operation.");
    }

    /**
     * Checks if the specified object exists in the stack.
     *
     * @param object the object to check for existence
     * @return true if the object exists in the stack, false otherwise
     */
  
    public boolean exists(Object object) {
        return stack.contains(object);
    }

    /**
     * Converts the stack to an array.
     *
     * @return an array containing all the elements in the stack
     */
   
    public Object[] toArray() {
        return stack.toArray();
    }

    /**
     * Clears the stack, removing all elements.
     */
    
    public void clear() {
        stack.clear();
    }

    /**
     * Returns the size of the stack.
     *
     * @return the number of elements in the stack
     */
    
    public int size() {
        return stack.size();
    }

    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator over the elements in the stack
     */
    
    public Iterator<T> iterator() {
        return stack.iterator();
    }
}







 * MyQueue represents a generic queue data structure.
 * It implements the MyList interface and provides queue-specific operations.
 *
 * @param <T> the type of elements stored in the queue
 *
 * Time Complexity:
 * - Adding an element to the queue: O(1)
 * - Removing an element from the queue: O(1)
 * - Checking for element existence: O(n)
 * - Converting the queue to an array: O(n)

public class MyQueue<T> implements MyList<T> {
    private LinkedList<T> queue;

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param data the element to be added
     */
    @Override
    public void addElement(T data) {
        queue.offer(data);
    }

    /**
     * Unsupported operation for queue.
     *
     * @param index the index at which the element is to be added
     * @param item  the element to be added
     */

    public void add(int index, T item) {
        throw new UnsupportedOperationException("Queue does not support adding elements at a specific index.");
    }

    /**
     * Unsupported operation for queue.
     *
     * @param index the index of the element to update
     * @param item  the new value of the element
     */

    public void set(int index, T item) {
        throw new UnsupportedOperationException("Queue does not support setting elements at a specific index.");
    }

    /**
     * Unsupported operation for queue.
     *
     * @param item the element to be added at the beginning
     */

    public void addFirst(T item) {
        throw new UnsupportedOperationException("Queue does not support adding elements at the beginning.");
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param item the element to be added at the end
     */
  
    public void addLast(T item) {
        addElement(item);
    }

    /**
     * Unsupported operation for queue.
     *
     * @param index the index of the element to retrieve
     * @return UnsupportedOperationException always thrown
     */
    
    public T get(int index) {
        throw new UnsupportedOperationException("Queue does not support random access.");
    }

    /**
     * Retrieves the first element of the queue.
     *
     * @return the first element of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    
    public T getFirst() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return queue.peek();
    }

    /**
     * Unsupported operation for queue.
     *
     * @return UnsupportedOperationException always thrown
     */
 
    public T getLast() {
        throw new UnsupportedOperationException("Queue does not support accessing the last element.");
    }

    /**
     * Unsupported operation for queue.
     *
     * @param index the index of the element to remove
     */

    public void remove(int index) {
        throw new UnsupportedOperationException("Queue does not support removing elements at a specific index.");
    }

    /**
     * Removes the first element of the queue.
     *
     * @throws NoSuchElementException if the queue is empty
     */

    public void removeFirst() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        queue.poll();
    }

    /**
     * Unsupported operation for queue.
     */

    public void removeLast() {
        throw new UnsupportedOperationException("Queue does not support removing elements at the end.");
    }

    /**
     * Removes the first element of the queue.
     * Alias for removeFirst().
     */
    public void remove() {
        removeFirst();
    }

    /**
     * Unsupported operation for queue.
     */

    public void sort() {
        throw new UnsupportedOperationException("Queue does not support sorting.");
    }

    /**
     * Unsupported operation for queue.
     */

    public int lastIndexOf(Object object) {
        throw new UnsupportedOperationException("Queue does not support lastIndexOf operation.");
    }

    /**
     * Checks if the specified object exists in the queue.
     *
     * @param object the object to check for existence
     * @return true if the object exists in the queue, false otherwise
     */
  
    public boolean exists(Object object) {
        return queue.contains(object);
    }

    /**
     * Converts the queue to an array.
     *
     * @return an array containing all the elements in the queue
     */
 
    public Object[] toArray() {
        return queue.toArray();
    }

    /**
     * Clears the queue, removing all elements.
     */

    public void clear() {
        queue.clear();
    }

    /**
     * Returns the size of the queue.
     *
     * @return the number of elements in the queue
     */

    public int size() {
        return queue.size();
    }

    /**
     * Returns an iterator over the elements in the queue.
     *
     * @return an iterator over the elements in the queue
     */
   
    public Iterator<T> iterator() {
        return queue.iterator();
    }
}





 * MyMinHeap represents a generic min-heap data structure.
 * It ensures that the smallest element is always at the root.
 *
 * @param <T> the type of elements stored in the min-heap, which must extend Comparable
 *
 * Time Complexity:
 * - Adding an element to the heap: O(log n)
 * - Getting the minimum element: O(1)
 * - Removing the minimum element: O(log n)
 * - Checking if the heap is empty: O(1)

public class MyMinHeap<T extends Comparable<T>> {

    private ArrayList<T> heap;

    /**
     * Constructs an empty min-heap.
     */
    public MyMinHeap() {
        heap = new ArrayList<>();
    }

    /**
     * Adds an element to the min-heap.
     *
     * @param data the element to be added
     */
    public void addElement(T data) {
        heap.add(data);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Retrieves the minimum element from the min-heap.
     *
     * @return the minimum element
     * @throws NoSuchElementException if the min-heap is empty
     */
    public T getMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap.get(0);
    }

    /**
     * Removes and returns the minimum element from the min-heap.
     *
     * @return the minimum element
     * @throws NoSuchElementException if the min-heap is empty
     */
    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    /**
     * Checks if the min-heap is empty.
     *
     * @return true if the min-heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Restores the heap property by moving an element up.
     *
     * @param index the index of the element to be heapified
     */
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    /**
     * Restores the heap property by moving an element down.
     *
     * @param index the index of the element to be heapified
     */
    private void heapifyDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;
        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    
     * Swaps two elements in the heap.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
