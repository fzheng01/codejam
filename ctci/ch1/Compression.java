import java.util.*;

public class Compression {
	public static String compression(String input) {
		int n = input.length();
		if(n <= 1) return input;
		StringBuffer compressed = new StringBuffer();
		char last = input.charAt(0);
		int counter = 1;
		for(int i = 1; i < n; i++) {
			char now = input.charAt(i);
			if(last == now) counter++;
			else {
				compressed.append(last);
				compressed.append(counter);
				last = now;
				counter = 1;
			}
			if(compressed.length() >= n) return input;
		}
		compressed.append(last);
		compressed.append(counter);
		return compressed.toString();
	}
	public static void main(String[] args) {
		String str = args[0];
		System.out.println(compression(str));
	}
}