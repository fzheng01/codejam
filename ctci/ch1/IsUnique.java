import java.util.*;

public class IsUnique {
	public static boolean isUnique(String input) {
		HashMap<Character, Boolean> map = new HashMap<Character, Boolean>();
		int n = input.length();
		if(n <= 1) return true;
		for(int i = 0; i < n; i++) {
			if(null != map.get(input.charAt(i))) return false;
			else map.put(input.charAt(i), true);
		}
		return true;
	}

	public static void main(String[] args) {
		String[] tester = {"", "a", "aa", "ab", "strong", "stronger", "Hello World"};
		for(String s : tester) {
			System.out.println(s + ": " + isUnique(s));
		}
	}
}