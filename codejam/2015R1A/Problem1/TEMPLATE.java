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
        pw.println(/*your result*/);
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
            new TEMPLATE().solution(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}