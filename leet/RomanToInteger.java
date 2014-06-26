import java.util.*;

public class RomanToInteger {
    public int romanToInt(String S) {
        String sUpper = S.toUpperCase();
        int n = S.length();
        if (n < 1) {
            return -1;
        }
        Map<Character, Integer> m = new HashMap<Character, Integer>(7);
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        if (m.get(sUpper.charAt(n - 1)) == null) {
            return -1;
        }
        int sum = m.get(sUpper.charAt(n - 1));
        for (int i = n - 2; i >= 0; --i) {
            if (m.get(sUpper.charAt(i)) == null) {
                return -1;
            }
            int curInt = m.get(sUpper.charAt(i));
            int rightInt = m.get(sUpper.charAt(i + 1));
            if (curInt >= rightInt) {
                sum += curInt;
            } else {
                sum -= curInt;
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
        RomanToInteger ri = new RomanToInteger();
        System.out.println(ri.romanToInt("XIV"));
    }
}