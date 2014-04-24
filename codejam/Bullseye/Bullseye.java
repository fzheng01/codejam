import java.io.*;
import java.util.*;

/**
 * The long data type is a 64-bit two's complement integer.
 * The signed long has a minimum value of -263 and a maximum value of 263-1.
 * In Java SE 8 and later, you can use the long data type to represent an unsigned 64-bit long,
 * which has a minimum value of 0 and a maximum value of 264-1.
 * The unsigned long has a minimum value of 0 and maximum value of 264-1.
 * Use this data type when you need a range of values wider than those provided by int.
 */
public class Bullseye {
    private final long r;
    private final long t;
    private final long result;
    
    Bullseye(long radius, long total) throws Exception {
        r = radius;
        t = total;
        /**
         * because n must satisfy
         * 2*n*n + (2*r-1)*n <= t
         * so 2*n*n < t --> n < sqrt(t)/2
         */
        result = binarySearch(1, (long) Math.sqrt(t/2.0));
    }
    
    /**
     * if f(n) <= t && f(n+1) > t, n is the answer
     * if f(n) > t, n is too large, return +1
     * if f(n+1) < t, n is too small, return -1
     */
    private int compareTo(long n) {
        /**
         * ret1 = t - (2*n*n + (2*r-1)*n)
         */
        long ret1 = t + n - 2*n*n; // n < sqrt(t/2), so n*n is safe
        if (ret1 < 0) return +1;
        if (ret1/n < 2*r) return +1; // r*n may exceed long type range
        ret1 -= 2*r*n;
        /**
         * ret2 = t - (2*(n+1)*(n+1) + (2*r-1)*(n+1))
         * ret2 = ret1 - (4*n+2*r+1)
         */
        long ret2 = ret1 - 1 - 4*n;
        if (ret2 < 0) return 0;
        ret2 -= 2*r;
        if (ret2 < 0) return 0;
        
        return -1;
    }
    
    private long binarySearch(long lo, long hi) throws Exception {
        if (lo > hi) throw new Exception("Invalid binary search boundary");
        long mid = (lo + hi)/2;
        if (compareTo(mid) < 0) return binarySearch(mid+1, hi);
        else if (compareTo(mid) > 0) return binarySearch(lo, mid-1);
        else return mid;
    }
    
    public String toString() {
        return Long.toString(result);
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            throw new IllegalArgumentException("Require input file name");
        Scanner sc = new Scanner(new FileReader(args[0]));
        String outFilename = args[0].replaceFirst("[.][^.]+$", "").concat(".out");
        PrintWriter pw = new PrintWriter(new FileWriter(outFilename));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            long r = sc.nextLong();
            long t = sc.nextLong();
            Bullseye bull = new Bullseye(r, t);
            pw.println(bull);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}