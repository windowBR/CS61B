package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
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

    public int getLength() {
        return items.length;
    }

    private void reduceSize() {
        // TODO: reduceSize
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
    public boolean isEmpty() {
        return size() == 0;
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
        if (isEmpty()){
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
    public Iterator<T> iterator() {
        // TODO: iterator
        return null;
    }
}
