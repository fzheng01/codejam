import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;
    
    /**
     * construct an empty randomized queue.
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
        N = 0;
    }
    
    /**
     * is the queue empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /**
     * return the number of items on the queue.
     */
    public int size() {
        return N;
    }
    
    /**
     * function to resize the array
     */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    
    /**
     * add the item.
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == a.length) {
            resize(2*a.length);
        }
        a[N++] = item;
    }
    
    /**
     * delete and return a random item.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int dequeIndex = StdRandom.uniform(N);
        Item item = a[dequeIndex];
        a[dequeIndex] = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }
    
    /**
     * return (but do not delete) a random item.
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return a[StdRandom.uniform(N)];
    }
    
    /**
     * return an independent iterator over items in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }
    
    private class RandomizedIterator implements Iterator<Item> {
        private int i;
        
        private RandomizedIterator() {
            i = N;
            if (N > 1) {
                StdRandom.shuffle(a, 0, N-1);
            }
        }
        
        public boolean hasNext() {
            return i > 0;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[--i];
        }
    }
    
    /**
     * unit testing.
     */
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                r.enqueue(item);
            } else if (!r.isEmpty()) {
                StdOut.print(r.dequeue() + " ");
            }
        }
        StdOut.println("(" + r.size() + " left on stack)");
        Iterator<String> iterator = r.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");
        }
        StdOut.println("");
    }
}