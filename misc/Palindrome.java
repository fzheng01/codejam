import java.util.*;

public class Palindrome {
    private static boolean isWord(char c) {
        if (c >= '0' && c <= '9') return true;
        if (c >= 'a' && c <= 'z') return true;
        if (c >= 'A' && c <= 'Z') return true;
        return false;
    }
    
    public static boolean isPalindrome(String str) {
        int len = str.length();
        if (len <= 1) return true;
        int i = 0; int j = len-1;
        while (i < j) {
            if (!isWord(str.charAt(i))) {
                i++;
                continue;
            }
            if (!isWord(str.charAt(j))) {
                j--;
                continue;
            }
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    
    public static void main(String[] args) throws IllegalArgumentException {
        if (args.length < 1)
            throw new IllegalArgumentException("Input string required");
        System.out.println(isPalindrome(args[0].toLowerCase()));
    }
}