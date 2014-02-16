import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("One command line arg required");
        }
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            r.enqueue(item);
        }
        if (k < 0 || k > r.size()) {
            throw new IllegalArgumentException("invalid k provided");
        }
        Iterator<String> iterator = r.iterator();
        while (k-- > 0) {
           StdOut.println(iterator.next());
        }
    }
}