import java.util.*;
import java.io.*;

public class StandingOvation {
    private void solution(Scanner sc, PrintWriter pw) {
        int maxShy = sc.nextInt();
        char[] audiences = sc.next().toCharArray();
        int stands = 0, ans = 0;
        for (int thres = 0; thres <= maxShy; thres++) {
            int sit = audiences[thres] - '0';
            if (sit > 0) {
                if (stands < thres) {
                    int delta = thres - stands;
                    ans += delta;
                    stands += delta;
                }
                stands += sit;
            }
        }
        pw.println(ans);
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
            new StandingOvation().solution(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
