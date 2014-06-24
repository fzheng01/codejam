package com.nufts.algorithms.sorting;

public class RSelection extends SortAlgorithm {
    private static int N;
    
    public static Comparable getRank(Comparable[] a, int rank) {
        N = a.length;
        if (rank <= 0 || rank > N)
            throw new IllegalArgumentException("Rank must between 1 and N");
        int rankIdx = rank - 1;
        int lo = 0, hi = N-1;
        while(true) {
            shuffle(a, lo, hi);
            int partIdx = partition(a, lo, hi);
            if (partIdx == rankIdx) {
                return a[partIdx];
            } else if (partIdx < rankIdx) {
                lo = partIdx + 1;
            } else if (partIdx > rankIdx) {
                hi = partIdx - 1;
            }
        }
    }
    
    private static int partition(Comparable[] a, int lo, int hi) {
        if (lo == hi) return lo;
        Comparable v = a[lo];
        int i = lo, j = hi+1;
        while(true) {
            while(less(a[++i], v)) {
                if (i == hi) break;
            }
            while(less(v, a[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}