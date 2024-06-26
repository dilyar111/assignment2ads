public interface MyList<T> extends Iterable<T> {
    void addElement(T data);

    void add(int index, T item);

    void set(int index, T item);

    void addFirst(T item);

    void addLast(T item);

    T get(int index);

    T getFirst();

    T getLast();

    void remove(int index);

    void removeFirst();

    void removeLast();

    void remove(); // Добавленный метод remove()

    void sort();

    int lastIndexOf(Object object);

    boolean exists(Object object);

    Object[] toArray();

    void clear();

    int size();
}

