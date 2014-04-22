import java.util.*;
import java.io.*;

public class ReverseWords {
    private final int len;
    private final String result;
    
    ReverseWords(String input) throws Exception {
        len = input.length();
        char[] sentence = input.toCharArray();
        if (len == 0) result = "";
        else {
            // first reverse the whole sentence
            doReverse(sentence, 0, len - 1);

            // then reverse every single word
            int head = 0, tail = 0;
            outer:
            while (head <= tail && tail < len) {
                while (sentence[head] == ' ') {
                    tail = ++head;
                    continue outer;
                }
                if (++tail == len || sentence[tail] == ' ') {
                    doReverse(sentence, head, tail - 1);
                    head = ++tail;
                }
            }
            result = new String(sentence);
        }
    }
    
    // private method to do the reverse
    // start and end index (inclusive)
    private void doReverse(char[] array, int start, int end) {
        if (start < 0 || end >= len || start > end)
            throw new IllegalArgumentException("Invalid start/end index");
        int i = start, j = end;
        while (i < j) {
            char temp = array[i];
            array[i++] = array[j];
            array[j--] = temp;
        }
    }

    public String toString() {
        return result;
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
            ReverseWords rw = new ReverseWords(sc.nextLine());
            pw.println(rw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}