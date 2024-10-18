package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    Node sentinel;

    private class Node {
        Node prev = null;
        Node next = null;
        T value;

        Node(T item) {
            value = item;
        }
    }


    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;

        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;

        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        size++;
    }

//    @Override
//    public boolean isEmpty() {
//        return size() == 0;
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {

        System.out.print("{ ");
        for (Node pointer = sentinel.next; pointer != sentinel; pointer = pointer.next) {
            System.out.print(pointer.value + ", ");
        }
        System.out.println("}");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node moved = sentinel.next;

        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;

        moved.prev = null;
        moved.next = null;
        return moved.value;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node moved = sentinel.prev;

        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;

        moved.prev = null;
        moved.next = null;
        return moved.value;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index >= size()) {
            return null;
        }
        Node pointer = sentinel;
        for (int i = 0; i < index + 1; i++) {
            pointer = pointer.next;
        }

        return pointer.value;
    }

    public T getRecursive(int index) {
        if (isEmpty() || index >= size()) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.value;
        } else {
            return getRecursiveHelper(current.next, index - 1);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedListDeque && ((LinkedListDeque<?>) obj).size() == size()) {
            Iterator<?> iterator = ((LinkedListDeque<?>) obj).iterator();
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
            private Node current = sentinel.next;

            @Override
            public boolean hasNext() {
                return current != sentinel;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}
