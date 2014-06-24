package com.nufts.algorithms.sorting;

import java.util.*;

public abstract class SortAlgorithm {
    
    public static void sort(Comparable[] a) { }
    
    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    protected static void shuffle(Comparable[] a) {
        Random rnd = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i+1);
            exch(a, index, i);
        }
    }
    
    protected static void shuffle(Comparable[] a, int lo, int hi) {
        if (lo == hi) return;
        Random rnd = new Random();
        for (int i = hi; i > lo; i--) {
            int index = rnd.nextInt(i-lo+1) + lo;
            exch(a, index, i);
        }
    }
    
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
}