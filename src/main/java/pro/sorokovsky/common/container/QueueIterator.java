package pro.sorokovsky.common.container;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class QueueIterator<T> implements Iterator<T> {
    private Node<T> current;

    public QueueIterator(Node<T> head) {
        this.current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        final var value = current.getValue();
        current = current.getNext();
        return value;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        Iterator.super.forEachRemaining(action);
    }
}
