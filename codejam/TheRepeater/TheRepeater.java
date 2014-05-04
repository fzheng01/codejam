import java.util.*;
import java.io.*;

public class TheRepeater {
    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            throw new IllegalArgumentException("Require input file name");
        Scanner sc = new Scanner(new FileReader(args[0]));
        String outFilename = args[0].replaceFirst("[.][^.]+$", "").concat(".out");
        PrintWriter pw = new PrintWriter(new FileWriter(outFilename));
        int caseCnt = Integer.parseInt(sc.nextLine());
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            int strCnt = Integer.parseInt(sc.nextLine());
            boolean solvable = true;
            ArrayList<Character> ch = new ArrayList<Character>();
            ArrayList[] arr = new ArrayList[strCnt];
          OUTER:
            for (int num_i = 0; num_i < strCnt; num_i++) {
              String str = sc.nextLine();
              if (!solvable) continue;
              arr[num_i] = new ArrayList();
              char cur = str.charAt(0);
              int cur_count = 1;
              int ref_index = 0;
              for (int i = 1; i < str.length(); i++) {
                char now = str.charAt(i);
                if (cur != now) {
                  if (num_i == 0) ch.add(cur);
                  else if (ch.get(ref_index++) != cur) {
                    solvable = false;
                    continue OUTER;
                  }
                  cur = now;
                  arr[num_i].add(cur_count);
                  cur_count = 1;
                } else cur_count++;
              }
              if (num_i == 0) ch.add(cur);
              else if (ref_index != ch.size()-1 || ch.get(ref_index) != cur) {
                  solvable = false;
                  continue OUTER;
              }
              arr[num_i].add(cur_count);
            }
            if (!solvable) pw.println("Fegla Won");
            else {
              int counter = 0;
              for (int i = 0; i < ch.size(); i++) {
                int mid = 0;
                for (int j = 0; j < strCnt; j++) {
                  mid = mid + (Integer)(arr[j].get(i));
                }
                mid = mid/strCnt;
                for (int j = 0; j < strCnt; j++) {
                  counter = counter + Math.abs((Integer)(arr[j].get(i)) - mid);
                }
              }
              pw.println(counter);
            }
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
