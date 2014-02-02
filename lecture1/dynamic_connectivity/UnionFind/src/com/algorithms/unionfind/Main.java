package com.algorithms.unionfind;

/**
 * Created with IntelliJ IDEA.
 * User: summitzf
 * Date: 2/2/14
 * Time: 12:29 PM
 * System output
 */
public class Main {
    public static void main(String[] args) {
        int n = 10;
        UnionFind unionFind = new UnionFind(n);
        System.out.println(unionFind.toString());
        unionFind.union(3, 4);
        unionFind.union(4, 9);
        System.out.println(unionFind.toString());
        unionFind.union(8, 0);
        unionFind.union(2, 3);
        System.out.println(unionFind.toString());
        unionFind.union(5, 6);
        unionFind.union(5, 9);
        System.out.println(unionFind.toString());
        unionFind.union(7, 3);
        unionFind.union(4, 8);
        System.out.println(unionFind.toString());
        unionFind.union(6, 1);
        System.out.println(unionFind.toString());
    }
}