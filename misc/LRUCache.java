import java.util.concurrent.*;

public class LRUCache<Key, Value> {
    private final int capacity;
    private ConcurrentLinkedQueue<Key> queue;
    private ConcurrentHashMap<Key, Value> map;
    LRUCache(int c) {
        this.capacity = c;
        queue = new ConcurrentLinkedQueue<Key>();
        map = new ConcurrentHashMap<Key, Value>();
    }
    
    public Value get(Key key) {
        return map.get(key);
    }
    
    public synchronized void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException();
        if (map.containsKey(key)) {
            queue.remove(key);
        }
        while (queue.size() >= capacity) {
            Key expiredKey = queue.poll();
            if (expiredKey != null) {
                map.remove(expiredKey);
            }
        }
        queue.add(key);
        map.put(key, value);
    }
}