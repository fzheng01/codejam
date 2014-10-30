public class Wow {
	private static int[] aux;
	public static void sort(int[] a) {
		int N = a.length;
		if (N <= 1) return;
		aux = new int[N];
		sort(a, 0, N-1);
	}
	private static void sort(int[] a, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi-lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}
	private static void merge(int[] a, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (aux[i] <= aux[j]) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
    public static void main(String[] main) {
        StringBuilder sb = new StringBuilder();
        String h1 = "HelloWorld";
        sb.append("Hello").append("World");
        if (h1.equals(sb.toString())) {
            System.out.println("They really match");
        }
    }
}