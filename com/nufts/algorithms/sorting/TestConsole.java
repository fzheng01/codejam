import java.util.Random;
import java.util.Arrays;
import com.nufts.algorithms.sorting.SelectionSort;
import com.nufts.algorithms.sorting.MergeSortTopDown;
import com.nufts.algorithms.sorting.QuickSort;

public class TestConsole {
    private static int N;
    private static boolean showArray;
    
    public static void main(String []args) {
        if (args.length != 2)
            throw new IllegalArgumentException("missing array size and showArray");
        N = Integer.parseInt(args[0]);
        if (N <= 0)
            throw new IllegalArgumentException("invalid array size");
        showArray = Boolean.parseBoolean(args[1]);
        run();
    }
    
    private static void run() {
        Comparable[] x = randIntArray();
        long startTime, totalTime;
        print(x);
        startTime = System.currentTimeMillis();
        SelectionSort.sort(x);
        totalTime = System.currentTimeMillis() - startTime;
        print(x);
        System.out.println("Selectionsort: " + totalTime + " millisec");
        
        x = randIntArray();
        print(x);
        startTime = System.currentTimeMillis();
        MergeSortTopDown.sort(x);
        totalTime = System.currentTimeMillis() - startTime;
        print(x);
        System.out.println("Mergesort Top Down: " + totalTime + " millisec");
        
        x = randIntArray();
        print(x);
        startTime = System.currentTimeMillis();
        QuickSort.sort(x);
        totalTime = System.currentTimeMillis() - startTime;
        print(x);
        System.out.println("Quicksort: " + totalTime + " millisec");
    }
    
    private static void print(Comparable[] x) {
        if (showArray) System.out.println(Arrays.toString(x));
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