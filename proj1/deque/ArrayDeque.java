package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items = (T[]) new Object[8];
    private int size;
    private int first;
    private int last;

    public ArrayDeque() {
        size = 0;
        first = 0;
        last = 0;
    }

    private void addSize() {
        if (isFull()) {
            int newSize = items.length * 2;
            createNewArray(newSize);
        }
    }

    private void createNewArray(int newSize) {
        T[] tmp = (T[]) new Object[newSize];
        for (int i = first, j = 0; j < size(); i = moveRight(i), j++) {
            tmp[j] = items[i];
        }

        first = 0;
        last = size();

        items = tmp;
    }

    private void reduceSize() {
        if (items.length >= 16 && size * 4 < items.length) {
            int newSize = items.length / 4;
            createNewArray(newSize);
        }
    }

    private int moveRight(int index) {
        return (index + 1) % items.length;
    }

    private int moveLeft(int index) {
        return (index + items.length - 1) % items.length;
    }

    private boolean isFull() {
        return size() == items.length;
    }

    @Override
    public void addFirst(T item) {
        addSize();
        first = moveLeft(first);
        items[first] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        addSize();
        items[last] = item;
        last = moveRight(last);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        boolean notPrintFirst = true;
        for (int i = first; notPrintFirst || i != last; i = moveRight(i)) {
            System.out.print(i + ", ");
            notPrintFirst = false;
        }
        System.out.println("]");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T tmp = items[first];
        items[first] = null;
        first = moveRight(first);
        size--;
        reduceSize();
        return tmp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        last = moveLeft(last);
        T tmp = items[last];
        items[last] = null;
        size--;
        reduceSize();
        return tmp;
    }

    @Override
    public T get(int index) {

        if (isEmpty()) {
            return null;
        }
        return items[(index + first) % items.length];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deque && ((Deque) obj).size() == size()) {
            Iterator iterator = ((Iterable) obj).iterator();
            for (T item : this) {
                if (!item.equals(iterator.next())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int current = first;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size();
            }

            @Override
            public T next() {
                T value = items[current];
                current = moveRight(current);
                count++;
                return value;
            }
        };

    }
}
