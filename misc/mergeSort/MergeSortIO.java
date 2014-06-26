import java.util.Random;
import java.util.Arrays;

public class MergeSortIO {
    public static void main(String []args) {
        if (args.length == 0) {
            System.out.println("missing array size integer");
        } else {
            int N = Integer.parseInt(args[0]);
            int[] x = new int[N];
            int i = 0;
            Random generator = new Random();
            while (i < N) {
                x[i++] = generator.nextInt(2*N);
            }
            System.out.println(Arrays.toString(x));
            MergeSortStandard ms = new MergeSortStandard(x);
            // MergeSortInPlace ms = new MergeSortInPlace(x);
            long startTime = System.currentTimeMillis();
            ms.mergeSort(0, N-1);
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println(ms.toString());
            System.out.println("MergeSort Running time = " + totalTime + " millisec");
        }
    }
}