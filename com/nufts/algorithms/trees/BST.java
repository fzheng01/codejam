package com.nufts.algorithms.trees;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class BST {
    private Node root;
    private class Node {
        private int val;
        private Node left, right;
        private int N;
        Node(int value, int n) {
            this.val = value;
            this.N = n;
        }
        public String toString() {
            return "Node[" + this.val + " (" + this.N + ")]";
        }
    }
    
    /**
     * get the size of subtree
     */
    public int size() {
        return size(root);
    }
    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }
    
    /**
     * put Node (value)
     */
    public void put(int value) {
        root = put(root, value);
    }
    private Node put(Node node, int value) {
        if (node == null) return new Node(value, 1); 
        if (value <= node.val) node.left = put(node.left, value);
        else node.right = put(node.right, value);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    
    /**
     * get Node (value)
     */
    public Node get(int value) {
        return get(root, value);
    }
    private Node get(Node node, int value) {
        if (node == null) return null;
        if (node.val == value) return node;
        else if (value < node.val) return get(node.left, value);
        else return get(node.right, value);
    }
    
    /**
     * contains (value)
     */
    public boolean contains(int value) {
        Node node = get(value);
        return node != null;
    }
    
    /**
     * traverse BST horizontally and return as array
     */
    public int[] traverse() {
        if (root == null) return new int[0];
        ArrayList<Integer> al = new ArrayList<Integer>();
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            al.add(current.val);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return listToArray(al);
    }
    
    /**
     * return sorted int array from BST
     */
    public int[] sort() {
        ArrayList<Integer> al = new ArrayList<Integer>();
        sort(root, al);
        return listToArray(al);
    }
    private void sort(Node node, ArrayList<Integer> al) {
        if (node != null) {
            sort(node.left, al);
            al.add(node.val);
            sort(node.right, al);
        }
    }
    
    /**
     * get node depth array from BST
     */
    public int[] getDepth(int k) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        getDepth(root, k, ret);
        Collections.sort(ret);
        return listToArray(ret);
    }
    private int[] getDepth(Node node, int target, ArrayList<Integer> ret) {
        if (node == null) return new int[]{};
        if (node.left == null && node.right == null) {
            if (target == 0) ret.add(node.val);
            return new int[]{0};
        }
        int[] depthL = getDepth(node.left, target, ret);
        int[] depthR = getDepth(node.right, target, ret);
        ArrayList<Integer> al = new ArrayList<Integer>();
        int i = 0, j = 0, nl = depthL.length, nr = depthR.length;
        int last = -1, cur;
        while (i < nl || j < nr) {
            if (i >= nl) cur = depthR[j++] + 1;
            else if (j >= nr) cur = depthL[i++] + 1;
            else if (depthL[i] <= depthR[j]) cur = depthL[i++] + 1;
            else cur = depthR[j++] + 1;
            if (last != cur) {
                last = cur;
                al.add(cur);
                if (cur == target)
                    ret.add(node.val);
            }
        }
        return listToArray(al);
    }
    
    /**
     * get least common ancester, assume x and y exist
     */
    public int getLCA(int a, int b) throws IllegalArgumentException {
        int x = a, y = b;
        if (!contains(x))
            throw new IllegalArgumentException("LCA arg " + x + " not in BST");
        if (!contains(y))
            throw new IllegalArgumentException("LCA arg " + y + " not in BST");
        if (x == y) return x;
        if (x > y) { int tmp = x; x = y; y = tmp; }
        Node ret = getLCA(root, x, y);
        if (ret == null)
            throw new NullPointerException("getLCA return null");
        else return ret.val;
    }
    private Node getLCA(Node node, int x, int y) {
        if (node == null) return null;
        if (node.val < x) return getLCA(node.right, x, y);
        else if (node.val > y) return getLCA(node.left, x, y);
        else return node;
    }
    
    /**
     * support function, convert Integer List to int array
     */
    public static int[] listToArray(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
    
    @Test
    public void testBSTBase() {
        /**
         *        8
         *    3      10
         *  1   6       14
         *     4 7     13
         *           11
         */
        int[] arr = {8, 3, 10, 6, 1, 4, 7, 14, 13, 11};
        BST bst = new BST();
        for (int x : arr) {
            bst.put(x);
        }
        // test get
        Node n = bst.get(4);
        org.junit.Assert.assertNotNull("node 4 should not be null", n);
        org.junit.Assert.assertEquals("node 4 value is not 4", 4, n.val);
        org.junit.Assert.assertEquals("node 4 size is not 1", 1, size(n));
        
        n = bst.get(8);
        org.junit.Assert.assertNotNull("node 8 should not be null", n);
        org.junit.Assert.assertEquals("node 8 value is not 8", 8, n.val);
        org.junit.Assert.assertEquals("node 8 size is not 10", 10, size(n));
        
        n = bst.get(2);
        org.junit.Assert.assertNull("node 2 should be null", n);
        
        n = bst.get(10);
        org.junit.Assert.assertNotNull("node 10 should not be null", n);
        org.junit.Assert.assertEquals("node 10 value is not 10", 10, n.val);
        org.junit.Assert.assertEquals("node 10 size is not 4", 4, size(n));
        
        // test sort
        int[] actualArray = bst.sort();
        int[] expectArray = new int[]{1, 3, 4, 6, 7, 8, 10, 11, 13, 14};
        org.junit.Assert.assertTrue("bst.sort result is not sorted",
                                    Arrays.equals(actualArray, expectArray));
        
        // test traverse
        actualArray = bst.traverse();
        expectArray = new int[]{8, 3, 10, 1, 6, 14, 4, 7, 13, 11};
        org.junit.Assert.assertTrue("bst.sort result is not sorted",
                                    Arrays.equals(actualArray, expectArray));
        
        // test depth
        actualArray = bst.getDepth(3);
        expectArray = new int[]{8, 10};
        org.junit.Assert.assertTrue("bst.getDepth depth 3 is not 8 and 10",
                                    Arrays.equals(actualArray, expectArray));
        actualArray = bst.getDepth(0);
        expectArray = new int[]{1, 4, 7, 11};
        org.junit.Assert.assertTrue("bst.getDepth leaves are incorrect",
                                    Arrays.equals(actualArray, expectArray));
        
        // test LCA
        org.junit.Assert.assertEquals("LCA of 4 and 7 is 6", 6, bst.getLCA(4, 7));
        org.junit.Assert.assertEquals("LCA of 11 and 14 is 14", 14, bst.getLCA(11, 14));
        org.junit.Assert.assertEquals("LCA of 1 and 4 is 3", 3, bst.getLCA(1, 4));
        org.junit.Assert.assertEquals("LCA of 7 and 11 is 8", 8, bst.getLCA(7, 11));
        try {
            bst.getLCA(2, 4);
            fail("getLCA didn't throw exception while it is expected to");
        } catch (IllegalArgumentException anException) {
            org.junit.Assert.assertEquals("incorrent exception", "LCA arg 2 not in BST", anException.getMessage());
        }
        org.junit.Assert.assertEquals("LCA of 13 and 7 is 8", 8, bst.getLCA(13, 7));
        org.junit.Assert.assertEquals("LCA of 13 and 10 is 10", 10, bst.getLCA(13, 10));
    }
    
    public static void main(String[] args) {
        System.out.println("Please run JUnit test");
        System.out.println("- 1st argument for getDepth");
        int[] arr = {8, 3, 10, 6, 1, 4, 7, 14, 13, 11};
        BST bst = new BST();
        for (int x : arr) {
            bst.put(x);
        }
        if (args.length > 0) {
            int k = Integer.parseInt(args[0]);
            System.out.println(Arrays.toString(bst.getDepth(k)));
        }
    }
}