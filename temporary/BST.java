public class BST {
    private Node root;
    private class Node {
        private int val;
        private Node left;
        private Node right;
        private int N;
        Node(int value, int n) {
            this.val = value;
            this.N = n;
        }
        public String toString() {
            return "Node[" + this.val + " (" + this.N + ")]";
        }
    }
    public int size() {
        return size(root);
    }
    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }
    public void put(int value) {
        root = put(root, value);
    }
    private Node put(Node node, int value) {
        if (node == null) return new Node(value, 1); 
        if (value <= node.val) {
            node.left = put(node.left, value);
        } else {
            node.right = put(node.right, value);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Node get(int value) {
        return get(root, value);
    }
    private Node get(Node node, int value) {
        if (node == null) return null;
        if (node.val == value) return node;
        else if (value < node.val) return get(node.left, value);
        else return get(node.right, value);
    }
    public void traverse() {
        Queue<Node> queue = new Queue<Node>();
        if (root == null) return;
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            Node current = queue.dequeue();
            System.out.print(current.val + " ");
            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }
    }
    public void sort() {
        sort(root);
    }
    private void sort(Node node) {
        if (node != null) {
            sort(node.left);
            System.out.print(node.val + " ");
            sort(node.right);
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
            bst.put(x);
        }
        System.out.println(bst.get(4));
        System.out.println(bst.get(8));
        System.out.println(bst.get(2));
        System.out.println(bst.get(10));
        bst.sort();
        System.out.println();
        bst.traverse();
        System.out.println();
    }
}