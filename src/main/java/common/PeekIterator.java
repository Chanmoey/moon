package common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * @author Chanmoey
 * @date 2022年05月12日
 */
public class PeekIterator<T> implements Iterator<T> {

    private static final int CACHE_SIZE = 10;

    private final Iterator<T> it;

    private final LinkedList<T> queueCache = new LinkedList<>();
    private final LinkedList<T> stackPutBack = new LinkedList<>();

    private T endToken;

    public PeekIterator(Stream<T> stream, T endToken) {
        this.it = stream.iterator();
        this.endToken = endToken;
    }

    public T peek() {
        if (this.stackPutBack.size() > 0) {
            return this.stackPutBack.getFirst();
        }
        if (!it.hasNext()) {
            return this.endToken;
        }
        T value = this.next();
        this.pushBack();
        return value;
    }

    public void pushBack() {
        if (this.queueCache.size() > 0) {
            this.stackPutBack.push(this.queueCache.pollLast());
        }
    }

    @Override
    public boolean hasNext() {
        return this.endToken != null || this.stackPutBack.size() > 0 || this.it.hasNext();
    }

    @Override
    public T next() {

        T value;
        if (this.stackPutBack.size() > 0) {
            value = this.stackPutBack.pop();
        } else {
            if (!this.it.hasNext()) {
                T temp = this.endToken;
                this.endToken = null;
                return temp;
            }
            value = this.it.next();
        }
        while (this.queueCache.size() > CACHE_SIZE - 1) {
            queueCache.poll();
        }
        this.queueCache.add(value);
        return value;
    }
}
