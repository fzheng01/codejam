import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;

class CountInversions {
	private static int[] aux;
	private static long counter = 0;

	public static long countInversions(int[] a) {
		int N = a.length;
		aux = new int[N];
		sort(a, 0, N-1);
		return counter;
	}

	private static void sort(int[] a, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}

	private static void merge(int[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else {
				if (aux[i] <= aux[j]) a[k] = aux[i++];
				else {
					counter += mid - i + 1;
					a[k] = aux[j++];
				}
			}
		}
	}

	private static int[] convertIntegers(ArrayList<Integer> integers) {
		int N = integers.size();
		int[] ret = new int[N];
		Iterator<Integer> iterator = integers.iterator();
		for (int i = 0; i < N; i++) {
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}

	public static void main(String[] args) {
		if (args.length == 0) throw new IllegalArgumentException();
		String fileName = args[0];
		ArrayList<Integer> input = new ArrayList<Integer>();
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				input.add(scanner.nextInt());
			}
			System.out.println(countInversions(convertIntegers(input)));
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}