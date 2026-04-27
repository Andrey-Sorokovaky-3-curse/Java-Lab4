package pro.sorokovsky.common.container;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private Node<T> head;
    private int count = 0;

    public int size() {
        return count;
    }

    public void push(T value) {
        final var node = new Node<>(value);
        if (head != null) node.setNext(head);
        head = node;
        count++;
    }

    public T pop() {
        if (head == null) throw new NoSuchElementException();
        final var node = head;
        head = head.getNext();
        count--;
        return node.getValue();
    }

    public T get(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        var current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) throw new NoSuchElementException();
            current = current.getNext();
        }
        if (current == null) throw new NoSuchElementException();
        return current.getValue();
    }

    public T peek() {
        if (head == null) throw new NoSuchElementException();
        return head.getValue();
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new StackIterator<>(head);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (obj instanceof Stack<?> stack) {
            if (stack.size() != size()) return false;
            for (var i = 0; i < stack.size(); i++) {
                if (!get(i).equals(stack.get(i))) return false;
            }
            return true;
        }
        else return false;
    }
}
