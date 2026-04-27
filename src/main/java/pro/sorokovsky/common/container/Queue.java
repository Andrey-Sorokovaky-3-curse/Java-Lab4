package pro.sorokovsky.common.container;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int count = 0;

    public void offer(T value) {
        final var node = new Node<>(value);
        if (tail == null) {
            head = tail = node;
        }
        else {
            tail.setNext(node);
            tail = node;
        }
        count++;
    }

    public T poll() {
        if (head == null) throw new NoSuchElementException();
        final var value = head.getValue();
        head = head.getNext();
        if (head == null) tail = null;
        count--;
        return value;
    }

    public T get(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index: " + index);
        var current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) throw new IndexOutOfBoundsException("Index: " + index);
            current = current.getNext();
        }
        if (current == null) throw new IndexOutOfBoundsException("Index: " + index);
        return current.getValue();
    }

    public T peek() {
        if (head == null) throw new NoSuchElementException();
        return head.getValue();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (obj instanceof Queue<?> queue) {
            if (queue.size() != size()) return false;
            for (var i = 0; i < queue.size(); i++) {
                if (!queue.get(i).equals(get(i))) return false;
            }
            return true;
        } else return false;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new QueueIterator<>(head);
    }
}
