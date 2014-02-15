import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    
    private class Node {
        private Item item;
        private Node pre;
        private Node next;
    }
    
    /**
     * construct an empty deque.
     */
    public Deque() {
        N = 0;
    }
    
    /**
     * is the queue empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /**
     * return the number of items on the deque.
     */
    public int size() {
        return N;
    }
    
    /**
     * insert the item at the front.
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.pre = null;
        first.next = oldfirst;
        if (isEmpty()) {
            last = first;
        }
        if (N == 1) {
            last.pre = first;
        }
        ++N;
    }
    
    /**
     * insert the item at the end.
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        if (isEmpty()) {
            first = last;
        }
        if (N == 1) {
            first.next = last;
        }
        ++N;
    }
    
    /**
     * delete and return the item at the front
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        --N;
        if (isEmpty()) {
            last = null;
        } else {
            first.pre = null;
        }
        return item;
    }
    
    /**
     * delete and return the item at the end
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.pre;
        --N;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }
    
    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    /**
     * unit testing
     */
    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            String operator = StdIn.readString();
            if (!item.equals("-")) {
                if (operator.equals("F")) {
                    q.addFirst(item);
                } else if (operator.equals("L")) {
                    q.addLast(item);
                }
            } else if (!q.isEmpty()) {
                if (operator.equals("F")) {
                    StdOut.print(q.removeFirst() + " ");
                } else if (operator.equals("L")) {
                    StdOut.print(q.removeLast() + " ");
                }
            }
        }
        StdOut.println("(" + q.size() + " left on stack)");
        Iterator<String> iterator = q.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");
        }
        StdOut.println("");
    }
}