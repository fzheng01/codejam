import java.util.*;

/**
* give me a number and print out all numbers cannot be divided by 5
* and 10 numbers a row
**/
public class DoNotGiveMeFive {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) throw new IllegalArgumentException();
		int n = Integer.parseInt(args[0]), i = 1, j = 1;
		do {
			if (i % 5 == 0) continue;
			System.out.print(i);
			System.out.print(j++ % 10 == 0 ? "\n" : "\t");
		} while(++i <= n);
		System.out.println();
	}
}