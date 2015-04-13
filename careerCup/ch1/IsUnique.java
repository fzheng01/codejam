import java.util.*;

public class IsUnique {
    public static boolean isUnique1(String input) {
        int checker = 0;
        int n = input.length();
        if (n <= 1) return true;
        for (int i = 0; i < n; i++) {
            int c = input.charAt(i) - 'a';
            if ((checker & (1 << c)) > 0) return false;
            checker |= (1 << c);
        }
        return true;
    }
    
    public static boolean isUnique2(String input) {
        int N = 256;
        int[] map = new int[N];
        int n = input.length();
        if (n <= 1) return true;
        for (int i = 0; i < n; i++) {
            int c = input.charAt(i);
            if (map[c] == 1) return false;
            map[c] = 1;
        }
        return true;
    }
    
    public static boolean isUnique3(String input) {
        HashMap<Character, Boolean> map = new HashMap<Character, Boolean>();
        int n = input.length();
        if (n <= 1) return true;
        for (int i = 0; i < n; i++) {
            if (null != map.get(input.charAt(i))) return false;
            else map.put(input.charAt(i), true);
        }
        return true;
    }
    
    public static void main(String[] args) {
        String[] tester = {"", "a", "aa", "ab", "strong", "stronger", "Hello World"};
        for (String s : tester) {
            System.out.println(s + ": " + isUnique1(s) + " | " + isUnique2(s) + " | " + isUnique3(s));
        }
    }
}