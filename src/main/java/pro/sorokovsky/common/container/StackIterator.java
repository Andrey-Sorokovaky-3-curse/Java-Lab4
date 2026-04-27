package pro.sorokovsky.common.container;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class StackIterator<T> implements Iterator<T> {
    private Node<T> current;

    StackIterator(Node<T> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        final var value = current.getValue();
        current = current.getNext();
        return value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }
}
