package com.algorithms.unionfind;

/**
 * Date: 2/2/14
 * Time: 12:29 PM
 * UnionFind Class
 */
public class UnionFind {
    private int id[];
    private int sz[];

    /**
     * constructor
     * @param N size of the nodes
     */
    public UnionFind(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * override toString method for output
     * @return String
     */
    public String toString() {
        String idStr = "";
        for (int i = 0; i < id.length; ++i) {
            idStr += id[i] + " ";
        }
        return idStr;
    }

    /**
     *
     * @param x node to get its root
     * @return root of the node
     */
    private int getRoot(int x) {
        while(id[x] != x) {
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }

    /**
     * method to find if 2 nodes connected
     * @param p node 1
     * @param q node 2
     * @return true if they are connected, false if not
     */
    public boolean find(int p, int q) {
        int i = getRoot(p);
        int j = getRoot(q);
        return i == j;
    }

    /**
     * method to union 2 nodes
     * @param p node 1
     * @param q node 2
     */
    public void union(int p, int q) {
        int pR = getRoot(p);
        int qR = getRoot(q);
        if(pR != qR) {
            if(sz[pR] >= sz[qR]) {
                sz[pR] += sz[qR];
                id[qR] = pR;
            } else {
                sz[qR] += sz[pR];
                id[pR] = qR;
            }
        }
    }
}
