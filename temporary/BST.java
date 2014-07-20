public class BST {
    private class Node {
        int value;
        Node left;
        Node right;
        int rank;
        Node(int v) {
            this.value = v;
        }
    }
    private Node root;
    public void add(int v) {
        if (root == null) root = new Node(v);
        else add(root, v);
    }
    private void add(Node n, int v) {
        if (n.value >= v) {
            if (n.left == null) n.left = new Node(v);
            else add(n.left, v);
        } else {
            if (n.right == null) n.right = new Node(v);
            else add(n.right, v);
        }
    }
    public void sort() {
        sort(root);
        System.out.println();
    }
    private void sort(Node n) {
        if (n != null) {
            sort(n.left);
            System.out.print(" " + n.value + " ");
            sort(n.right);
        }
    }
    public static void main(String[] args) {
        /**
         *        8
         *    3      10
         *  1   6       14
         *    4   7  13
         */
        int[] arr = {8, 3, 10, 6, 1, 4, 7, 14, 13};
        BST bst = new BST();
        for (int x : arr) {
            bst.add(x);
        }
        bst.sort();
    }
}