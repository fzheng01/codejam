import java.util.Random;

public class Inversion {
    private int[] array;
    
    public Inversion(int[] a) {
        array = a;
    }
    
    public void mergeSort(int start, int end) {
        int middle;
        if (start > end || start < 0 || end >= array.length) {
            throw new ArrayIndexOutOfBoundsException("");
        }
        if (end > start) {
            middle = (start + end)/2;
            mergeSort(start, middle);
            mergeSort(middle+1, end);
            doMerge(start, middle+1, end);
        }
    }
    
    public void doMerge(int lStart, int rStart, int end) {
        int[] tmpArray = new int[end-lStart+1];
        int t = 0;
        int i = lStart;
        int j = rStart;
        while (i < rStart && j <= end) {
            if (array[i] <= array[j]) {
                tmpArray[t++] = array[i++];
            } else {
                tmpArray[t++] = array[j++];
            }
        }
        if (i > rStart-1) {
            while (j <= end) {
                tmpArray[t++] = array[j++];
            }
        }
        if (j > end) {
            while (i < rStart) {
                tmpArray[t++] = array[i++];
            }
        }
        t = 0;
        i = lStart;
        while (t <= end-lStart) {
            array[i++] = tmpArray[t++];
        }
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int val: array) {
            str.append(val);
            str.append(" ");
        }
        return str.toString();
    }
    
    public static void main(String []args) {
        if (args.length == 0) {
            System.out.println("missing array size integer");
        } else {
            int N = Integer.parseInt(args[0]);
            int[] x = new int[N];
            int i = 0;
            Random generator = new Random();
            while (i < N) {
                x[i++] = generator.nextInt(N);
            }
            Inversion inv = new Inversion(x);
            long startTime = System.currentTimeMillis();
            inv.mergeSort(0, N-1);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
//            System.out.println(inv.toString());
            System.out.println("Running time = " + totalTime + " millisec");
        }
    }
}
