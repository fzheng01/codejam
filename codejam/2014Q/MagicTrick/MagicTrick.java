import java.util.*;
import java.io.*;

public class MagicTrick {
    private final boolean[] cardArray;
    private int answer = 0;
    
    public MagicTrick(int[] rowFirst, int[] rowSecond) {
        cardArray = new boolean[17];
        for (int i : rowFirst) cardArray[i] = true;
        boolean found = false;
        for (int j : rowSecond) {
            if (cardArray[j]) {
                if (found) {
                    answer = -1;
                    break;
                } else {
                    found = true;
                    answer = j;
                }
            }
        }
    }
    
    public String toString() {
        if (answer == 0) return "Volunteer cheated!";
        else if (answer == -1) return "Bad magician!";
        else return "" + answer;
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new IllegalArgumentException("Require input and output filenames");
        Scanner sc = new Scanner(new FileReader(args[0]));
        PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
        int caseCnt = sc.nextInt();
        int dim = 4;
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            int[] x = new int[dim];
            int[] y = new int[dim];
            int n1 = sc.nextInt();
            for (int i = 1; i <= dim; i++) {
                for (int j = 0; j < dim; j++) {
                    int num = sc.nextInt();
                    if (n1 == i) {
                        try {
                            x[j] = num;
                        } catch (NumberFormatException nfe) {
                            System.out.println(nfe);
                        }
                    }
                }
            }
            int n2 = sc.nextInt();
            for (int i = 1; i <= dim; i++) {
                for (int j = 0; j < dim; j++) {
                    int num = sc.nextInt();
                    if (n2 == i) {
                        try {
                            y[j] = num;
                        } catch (NumberFormatException nfe) {
                            System.out.println(nfe);
                        }
                    }
                }
            }
            MagicTrick mt = new MagicTrick(x, y);
            pw.println(mt);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}