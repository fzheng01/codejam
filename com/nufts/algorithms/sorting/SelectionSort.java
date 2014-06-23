package com.nufts.algorithms.sorting;

public class SelectionSort extends SortAlgorithm {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[i])) exch(a, j, i);
            }
        }
    }
}