import java.util.*;

public class BinaryWildCard {
    static void binaryWildCard (String bin) {
        if (bin.length() < 1) return;
        transfer(bin, 0);
    }
    
    private static void transfer (String input, int offset) {
        int len = input.length();
        if (offset >= len) {
            System.out.println(input);
            return;
        }
        StringBuilder sb = new StringBuilder(input);
        for (int i = offset; i < len; i++) {
            if (sb.charAt(i) == '?') {
                sb.setCharAt(i, '0');
                transfer(sb.toString(), i+1);
                sb.setCharAt(i, '1');
                transfer(sb.toString(), i+1);
                return;
            }
        }
        System.out.println(sb.toString());
    }
    
    public static void main (String[] args) {
        String x = "??";
        binaryWildCard(x);
    }
}