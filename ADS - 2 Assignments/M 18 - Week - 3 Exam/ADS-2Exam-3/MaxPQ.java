/**
 * Comparator import.
 */
import java.util.Comparator;
/**
 * Iterator import.
 */
import java.util.Iterator;
/**
 * No such element exception.
 */
import java.util.NoSuchElementException;

/**
 * Class for maximum pq.
 *
 * @param      <Key>  The key
 */
public class MaxPQ<Key> implements Iterable<Key> {
    /**
     * store items at indices 1 to n.
     */
    private Key[] pq;
    /**
     * number of items on priority queue.
     */
    private int n;
    /**
     * optional comparator.
     */
    private Comparator<Key> comparator;

    /**
     * Initializes an empty priority queue
     * with the given initial capacity.
     *
     * @param  initCapacity the initial
     * capacity of this priority queue
     */
    public MaxPQ(final int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue
     * with the given initial capacity,
     * using the given comparator.
     *
     * @param  initCapacity the initial
     * capacity of this priority queue
     * @param  comparator1 the order in which
     * to compare the keys
     */
    public MaxPQ(final int initCapacity,
        final Comparator<Key> comparator1) {
        this.comparator = comparator1;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority
     * queue using the given comparator.
     *
     * @param  comparator1 the order in
     * which to compare the keys
     */
    public MaxPQ(final Comparator<Key> comparator1) {
        this(1, comparator1);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number
     * of keys, using sink-based heap construction.
     *
     * @param  keys the array of keys
     */
    public MaxPQ(final Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }



    /**
     * Returns true if this priority
     * queue is empty.
     *
     * @return {@code true} if this
     * priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys
     * on this priority queue.
     *
     * @return the number of keys on
     * this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException
     * if this priority queue is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                "Priority queue underflow");
        }
        return pq[1];
    }

    /**
     * Resize function.
     *
     * @param      capacity  The capacity
     */
    private void resize(final int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the new key to
     * add to this priority queue
     */
    public void insert(final Key x) {

        // double size of array if necessary
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }

        // add x, and percolate it up to
        //maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    /**
     * Removes and returns a largest key on
     * this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this
     * priority queue is empty
     */
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                "Priority queue underflow");
        }
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        final int four = 4;
        if ((n > 0) && (n == (pq.length - 1) / four)) {
            resize(pq.length / 2);
        }
        assert isMaxHeap();
        return max;
    }

    /**
     * Swim function.
     *
     * @param      k     { parameter_description }
     */
    private void swim(final int k) {
        int p = k;
        while (p > 1 && less(p / 2, p)) {
            exch(p, p / 2);
            p = p / 2;
        }
    }

    /**
     * Sink function.
     *
     * @param      k     { parameter_description }
     */
    private void sink(final int k) {
        int a = k;
        while (2 * a <= n) {
            int j = 2 * a;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(a, j);
            a = j;
        }
    }
    /**
     * less function.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean less(final int i, final int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }
    /**
     * Exchange functionn.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     */
    private void exch(final int i, final int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..N] a max heap?

    /**
     * Determines if maximum heap.
     *
     * @return     True if maximum heap, False otherwise.
     */
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?

    /**
     * Determines if maximum heap.
     *
     * @param      k     { parameter_description }
     *
     * @return     True if maximum heap, False otherwise.
     */
    private boolean isMaxHeap(final int k) {
        if (k > n) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left  <= n && less(k, left)) {
            return false;
        }
        if (right <= n && less(k, right)) {
            return false;
        }
        return isMaxHeap(left) && isMaxHeap(right);
    }

    /**
     * Returns an iterator that iterates over
     * the keys on this priority queue
     * in descending order.
     * The iterator doesn't implement {@code
     * remove()} since it's optional.
     *
     * @return an iterator that iterates over
     * the keys in descending order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    /**
     * Class for heap iterator.
     */
    private class HeapIterator implements Iterator<Key> {
        /**
         * create a new pq.
         */
        private MaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already
        // in heap order so no keys move

        /**
         * Constructs the object.
         */
        protected HeapIterator() {
            if (comparator == null) {
                copy = new MaxPQ<Key>(size());
            } else {
                copy = new MaxPQ<Key>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        /**
         * Remove function.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * Next function.
         *
         * @return     { description_of_the_return_value }
         */
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }
    }
}
