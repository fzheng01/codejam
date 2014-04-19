import java.util.*;
import java.io.*;

public class DeceitfulWar {
    private final float[] naomi;
    private final float[] ken;
    private final int dim;
    private final int scoreOfDeceitfulWar;
    private final int scoreOfWar;
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
        scoreOfDeceitfulWar = getScore(ken, naomi);
        scoreOfWar = dim - getScore(naomi, ken);
    }
    
    /**
     * Deceitful war is actually a mirror of war
     */
    private int getScore(float[] foe, float[] amigo) {
        int i = 0, j = 0, score = 0;
        while (i < dim && j < dim) {
            int id = getFirstElementLargerThanKey(foe[i], amigo, j, dim-1);
            if (id >= dim) break;
            j = id + 1;
            i++;
            score++;
        }
        return score;
    }
    
    /**
     * binary search to find the first larger element than the key
     * sorted array start index 'start'; end index 'end' (inclusive)
     */
    private int getFirstElementLargerThanKey(float key, float[] array, int start, int end) {
        int n = array.length, mid = 0, lo = start, hi = end;
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