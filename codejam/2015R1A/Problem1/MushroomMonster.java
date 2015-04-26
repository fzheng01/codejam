import java.util.*;
import java.io.*;

public class MushroomMonster {
    void solution(Scanner sc, PrintWriter pw) {
        /**
         * for integer:
         *     x = sc.nextInt();
         * for long:
         *     x = sc.nextLong();
         * for double:
         *     x = Double.parseDouble(sc.next());
         * for charArray:
         *     x = sc.next().toCharArray();
         **/
        int N = sc.nextInt();
        int[] m = new int[N];
        long counter1 = 0;
        long counter2 = 0;
        double max_rate = 0.0;
        for (int i = 0; i < N; i++) {
            m[i] = sc.nextInt();
            if (i > 0) {
                if (m[i] < m[i-1]) {
                    int delta = m[i-1] - m[i];
                    counter1 += delta;
                    double rate = delta/10.0;
                    if (rate > max_rate) {
                        max_rate = rate;
                    }
                }
            }
        }
        for (int i = 1; i < N; i++) {
            counter2 += Math.min(m[i-1], (long)(10*max_rate));
        }
        pw.printf("%d %d\n", counter1, counter2);
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException("Require input file name");
        }
        Scanner sc = new Scanner(new FileReader(args[0]));
        String outFilename = args[0].replaceFirst("[.][^.]+$", "").concat(".out");
        PrintWriter pw = new PrintWriter(new FileWriter(outFilename));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            new MushroomMonster().solution(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}