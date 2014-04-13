import java.util.*;
import java.io.*;

// having issue on score of war
public class DeceitfulWar {
    private float[] naomi;
    private float[] ken;
    private final int dim;
    private int scoreOfDeceitfulWar;
    private int scoreOfWar;
    public DeceitfulWar(float[] a, float[] b) {
        int i, j;
        dim = a.length;
        naomi = new float[dim];
        ken = new float[dim];
        for (i = 0; i < dim; i++) {
            naomi[i] = a[i];
            ken[i] = b[i];
        }
        Arrays.sort(naomi);
        Arrays.sort(ken);
        int tooSmallNaomiBlocks = getFirstLargerId(ken[0], naomi, 0, dim-1);
        int tooLargeKenBlocks = dim - getFirstLargerId(naomi[dim-1], ken, 0, dim-1);
        scoreOfDeceitfulWar = dim - Math.max(tooSmallNaomiBlocks, tooLargeKenBlocks);
        i = 0;
        j = 0;
        scoreOfWar = dim;
        while (i < dim && j < dim) {
            int id = getFirstLargerId(naomi[i], ken, j, dim-1);
            if (id >= dim) break;
            j = id + 1;
            i++;
            scoreOfWar--;
        }
    }
    
    private int getFirstLargerId(float key, float[] array, int a, int b) {
        int n = array.length, lo = a, hi = b;
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo)/2;
            if (key < array[mid]) hi = mid - 1;
            else {
                if (mid == n-1) return n;
                if (key < array[mid+1]) return mid + 1;
                lo = mid + 1;
            }
        }
        return mid;
    }
    
    public String toString() {
        return scoreOfDeceitfulWar + " " + scoreOfWar;
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new IllegalArgumentException("Require input and output filenames");
        Scanner sc = new Scanner(new FileReader(args[0]));
        PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            int num = sc.nextInt();
            float[] naomiBlocks = new float[num];
            float[] kenBlocks = new float[num];
            for (int i = 0; i < num; i++) {
                naomiBlocks[i] = sc.nextFloat();
            }
            for (int i = 0; i < num; i++) {
                kenBlocks[i] = sc.nextFloat();
            }
            DeceitfulWar dw = new DeceitfulWar(naomiBlocks, kenBlocks);
            pw.println(dw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}