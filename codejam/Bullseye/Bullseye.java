import java.io.*;
import java.util.*;

public class Bullseye {
    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            throw new IllegalArgumentException("Require input file name");
        Scanner sc = new Scanner(new FileReader(args[0]));
        String outFilename = args[0].replaceFirst("[.][^.]+$", "").concat(".out");
        PrintWriter pw = new PrintWriter(new FileWriter(outFilename));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            // double r = sc.nextDouble();
            // double t = sc.nextDouble();
            // double result = 0.5*(Math.sqrt((r-0.5)*(r-0.5) + 2.0*t)-(r-0.5));
            // long n = (long) Math.floor(result);
            // if (t < 2.0*n*n+(2.0*r-1)*n) n--;
            long r = sc.nextLong();
            long t = sc.nextLong();
            long n = 0;
            do {
                t -= 4*(++n)+2*r-3;
            } while (t > 0);
            pw.println(--n);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}