import java.util.*;

public class IntegerToRoman {
    public String intToRoman(int num) {
        if (num <= 0) {
            return "";
        }
        final int n = 7;
        Map<Integer, Character> m = new HashMap<Integer, Character>(n);
        m.put(1, 'I');
        m.put(5, 'V');
        m.put(10, 'X');
        m.put(50, 'L');
        m.put(100, 'C');
        m.put(500, 'D');
        m.put(1000, 'M');
        int a[] = new int[] { 1000, 500, 100, 50, 10, 5, 1 };
        StringBuilder str = new StringBuilder();
        int i = 0;
        while (num > 0 && i < n) {
            int div = num / a[i];
            num = num % a[i];
            if (div == 1 && i > 0 && i < n - 1 && num/a[i+1] == 4) {
                str.append(m.get(a[i+1])).append(m.get(a[i-1]));
                num %= a[i+1];
                i += 2;
                continue;
            }
            if (div == 4 && i > 0) {
                str.append(m.get(a[i])).append(m.get(a[i - 1]));
                div = 0;
            }
            while (div > 0) {
                str.append(m.get(a[i]));
                --div;
            }
            ++i;
        }
        return str.toString();
    }
    
    public static void main(String[] args) {
        IntegerToRoman ir = new IntegerToRoman();
        System.out.println(ir.intToRoman(12));
    }
}