import java.util.*;
import java.io.*;

public class TheRepeater {
    private StringBuilder compress = new StringBuilder();
    private ArrayList<Integer> dArray = new ArrayList<Integer>();
    
    TheRepeater(String word) {
        int wordLen = word.length();
        int duplicateCount = 0;
        compress.append(word.charAt(0));
        for (int i = 1; i < wordLen; i++) {
            char cur = word.charAt(i);
            if (cur == word.charAt(i-1)) {
                duplicateCount++;
            } else {
                compress.append(cur);
                dArray.add(duplicateCount);
                duplicateCount = 0;
            }
        }
        dArray.add(duplicateCount);
    }
    
    public String getCompress() {
        return compress.toString();
    }
    
    public int[] getDist() {
        int len = dArray.size();
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            ret[i] = (Integer) dArray.get(i);
        }
        return ret;
    }

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
            String share = null;
            int[][] dists = new int[strCnt][];
            for (int strIdx = 0; strIdx < strCnt; strIdx++) {
                String str = sc.nextLine();
                if (str.isEmpty()) solvable = false;
                if (!solvable) continue;
                TheRepeater tp = new TheRepeater(str);
                if (strIdx == 0) share = tp.getCompress();
                else solvable = share.equals(tp.getCompress());
                dists[strIdx] = tp.getDist();
            }
            if (!solvable) pw.println("Fegla Won");
            else {
                int shareLen = share.length();
                int sum = 0;
                for (int k = 0; k < shareLen; k++) {
                    // for each character, count the minimal sum of absolute values (diff)
                    // for example, input are abb and aaab
                    // unique/fingerprint string is ab
                    // dist arrays are [0, 1] and [2, 0] respectively
                    // to make them the same, for character a, median is 0, minimal step is 2
                    // for character b, median is 0, minimal step is 1
                    // so the total is 3
                    int[] dist = new int[strCnt];
                    for (int i = 0; i < strCnt; i++) {
                        dist[i] = dists[i][k];
                    }
                    // strCnt is not large (<=100), so just sort it by nlog(n)
                    Arrays.sort(dist);
                    // minimum sum of absolute values -> median
                    int median = (strCnt%2 == 0) ? dist[strCnt/2] : dist[(strCnt-1)/2];
                    for (int i = 0; i < strCnt; i++) {
                        if (dist[i] > median) {
                            sum += dist[i] - median;
                        } else {
                            sum += median - dist[i];
                        }
                    }
                }
                pw.println(sum);
            }
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
