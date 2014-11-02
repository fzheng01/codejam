import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;

class TotalComparisons {
	private static long counter;
	private static int id;

	public static long sort(int[] a) {
		counter = 0;
        sort(a, 0, a.length-1);
        return counter;
    }
    
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    
    private static int partition(int[] a, int lo, int hi) {
        int v = getPivot(a, lo, hi);
        counter += hi-lo;
        int i = lo + 1;
        for (int j = lo+1; j <= hi; j++) {
        	if (a[j] < v) {
        		exch(a, j, i);
        		i = i + 1;
        	}
        }
        exch(a, lo, --i);
        return i;
    }

    private static int getPivot(int[] a, int lo, int hi) {
    	switch (id) {
    		case 1:
    			return a[lo];
    		case 2:
    			exch(a, lo, hi);
    			return a[lo];
    		case 3:
    			if (hi - lo < 2) return a[lo];
    			else {
    				int chosen = getMedian(a, lo, hi, (lo+hi)/2);
    				exch(a, lo, chosen);
    				return a[lo];
    			}
    		default:
    			throw new IllegalArgumentException();
    	}
    }

    private static int getMedian(int[] a, int x, int y, int z) {
    	int small, large, median;
    	if (less(a[x], a[y])) {
    		large = y;
    		small = x;
    	} else {
    		large = x;
    		small = y;
    	}
    	if (less(a[large], a[z])) return large;
    	if (less(a[z], a[small])) return small;
    	return z;
    }

    private static void exch(int[] a, int x, int y) {
    	int md = a[x];
    	a[x] = a[y];
    	a[y] = md;
    }

    private static boolean less(int x, int y) {
    	if (x < y) return true;
    	else return false;
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
		if (args.length != 2) throw new IllegalArgumentException();
		String fileName = args[0];
		id = Integer.parseInt(args[1]);
		ArrayList<Integer> input = new ArrayList<Integer>();
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				input.add(scanner.nextInt());
			}

			System.out.println(sort(convertIntegers(input)));
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}