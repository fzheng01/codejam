import java.util.*;

public class FinalExam {
	public static void main(String[] args) {
		TestClass tc = new TestClass();
		tc.main(new String[]{});
	}
}

class TestClass {
	int field;

	void field(int x) {
		// method can have same name as field
		System.out.println("field is " + x);
	}

	public static void main(String[] args) {
		try {
			int[][] a = null;
			// int[][] a = new int[2];  // FinalExam.java:3: error: incompatible types
			a[0] = new int[3]; 	// it will have NullPointerException, but will pass compile
			a[1] = new int[6]; 
		} catch(Exception e) {
			System.out.println(e.getClass().getName());
		}
		float divFloat = 1.0f % 3.0f;
		int divInt = 1 % 3;
		System.out.println("divFloat : divInt = " + divFloat + " : " + divInt);
		float checkFloat = (1334_5.0f > 1243_3.0f) ? 12_345 : 12_345.20f;
		checkFloat += 1024;
		System.out.println("checkFloat = " + checkFloat);
		ArrayList<String> as = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e"));
		int counter = 0;
		for (String str : as) {
			switch(str) {
				case "a":
					continue;
				case "b":
					counter++;
					break;
				case "c":
					counter++;
					continue;
				case "d":
					counter++;
					break;
			}
		}
		System.out.println("The counter is " + counter);

		System.out.println("World");
	}
}