import java.util.Random;
import java.util.Arrays;
import com.nufts.algorithms.sorting.MergeSortTopDown;

public class TestConsole {
    private static int N;
    
    public static void main(String []args) {
        if (args.length == 0) throw new IllegalArgumentException("missing array size integer");
        N = Integer.parseInt(args[0]);
        if (N <= 0) throw new IllegalArgumentException("invalid array size");
        testMergeSortTopDown(randIntArray());
    }
    
    private static void testMergeSortTopDown(Comparable[] x) {
        System.out.println(Arrays.toString(x));
        long startTime = System.currentTimeMillis();
        MergeSortTopDown.sort(x);
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(Arrays.toString(x));
        System.out.println("MergeSort Running time = " + totalTime + " millisec");
    }
    
    private static Integer[] randIntArray() {
        Integer[] x = new Integer[N];
        int i = 0;
        Random generator = new Random();
        while (i < N) {
            x[i++] = generator.nextInt(2*N);
        }
        return x;
    }
}