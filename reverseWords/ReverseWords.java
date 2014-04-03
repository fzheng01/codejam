import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ReverseWords {
    private char[] sentence;
    private int sentenceLength;
    
    ReverseWords(String input) {
        sentenceLength = input.length();
        sentence = input.toCharArray();
    }
    
    private void doReverse(int start, int end) {
        if (start < 0 || end >= sentenceLength || start > end)
            throw new IllegalArgumentException("Unaccepted start or end index");
        int i = start, j = end;
        while (i < j) {
            char temp = sentence[i];
            sentence[i++] = sentence[j];
            sentence[j--] = temp;
        }
    }
    
    public String doReverse() {
        if (sentenceLength == 0) return "";
        try {
            // first reverse the whole sentence
            doReverse(0, sentenceLength - 1);
            
            // then reverse every single word
            int head = 0;
            int tail = 0;
            
            outer:
            while (head <= tail && tail < sentenceLength) {
                while (sentence[head] == ' ') {
                    tail = ++head;
                    continue outer;
                }
                if (++tail == sentenceLength || sentence[tail] == ' ') {
                    doReverse(head, tail - 1);
                    head = ++tail;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return new String(sentence);
    }
    
    public static void main(String[] args) throws IOException {
        if (args.length != 2)
            throw new IllegalArgumentException("Two arguments required");
        
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(args[0]));
            bufferedWriter = new BufferedWriter(new FileWriter(args[1]));
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                ReverseWords rw = new ReverseWords(s);
                String rs = rw.doReverse();
                bufferedWriter.write(rs);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                bufferedReader.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
        }
    }
}