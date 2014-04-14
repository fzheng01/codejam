public class KdTree {
    private Node root;
    private int size;
    
    private static class Node {
        private Point2D p;
        private RectHV rect;
        private boolean isRed;
        private Node lb;
        private Node rt;
        public Node(Point2D p, RectHV rect, boolean isRed) {
            this.p = p;
            this.rect = rect;
            this.isRed = isRed;
        }
        public Point2D p() {
            return this.p;
        }
        public boolean isRed() {
            return this.isRed;
        }
        public RectHV getRect() {
            return this.rect;
        }
        public boolean isLBNext(Point2D that) {
            int compareResult;
            if (isRed) compareResult = compareTo1D(p.x(), that.x());
            else compareResult = compareTo1D(p.y(), that.y());
            return compareResult > 0;
        }
        public Node getSubtree(boolean isLB) {
            if (isLB) return lb;
            else return rt;
        }
        public Node getSubtree(Point2D that) {
            boolean isLB = isLBNext(that);
            if (isLB) return lb;
            else return rt;
        }
        private int compareTo1D(double x, double y) {
            if (x < y) return -1;
            if (x > y) return +1;
            return 0;
        }
    }
    
    // construct an empty set of points
    public KdTree() {
        this.size = 0;
    }
    
    // is the set empty ?
    public boolean isEmpty() {
        return root == null;
    }
    
    // number of points in the set
    public int size() {
        return this.size;
    }
    
    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (contains(p)) return;
        Node newNode;
        this.size++;
        if (isEmpty()) {
            newNode = new Node(p, new RectHV(0.0, 0.0, 1.0, 1.0), true);
            root = newNode;
            return;
        }
        Node current = root;
        Node pre = current;
        boolean isLBNext = false;
        while (current != null) {
            isLBNext = current.isLBNext(p);
            pre = current;
            current = current.getSubtree(isLBNext);
        }
        RectHV preRect = pre.getRect();
        double xmin = preRect.xmin();
        double xmax = preRect.xmax();
        double ymin = preRect.ymin();
        double ymax = preRect.ymax();
        boolean isRed = pre.isRed();
        if (isRed) {
            if (isLBNext) xmax = pre.p().x();
            else xmin = pre.p().x();
        } else {
            if (isLBNext) ymax = pre.p().y();
            else ymin = pre.p().y();
        }
        RectHV curRect = new RectHV(xmin, ymin, xmax, ymax);
        newNode = new Node(p, curRect, !isRed);
        if (isLBNext) pre.lb = newNode;
        else pre.rt = newNode;
    }
    
    private String print(Node node) {
        StringBuilder str = new StringBuilder();
        str.append("point: " + node.p() + "\n");
        str.append("rect: " + node.getRect() + "\n");
        str.append("isRed: " + node.isRed() + "\n");
        str.append("Totalsize: " + this.size);
        return str.toString();
    }
    
    // does the set contain the point p?
    public boolean contains(Point2D p) {
        Node current = root;
        while (current != null) {
            if (current.p().equals(p)) return true;
            current = current.getSubtree(p);
        }
        return false;
    }
    
    private void draw(Node node) {
        if (node != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            node.p().draw();
            RectHV rect = node.getRect();
            StdDraw.setPenRadius();
            if (node.isRed()) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.p().x(), rect.ymin(), node.p().x(), rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(rect.xmin(), node.p().y(), rect.xmax(), node.p().y());
            }
            draw(node.getSubtree(true));
            draw(node.getSubtree(false));
        }
    }
    
    // draw all of the points to standard draw
    public void draw() {
        draw(root);
    }
    
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> subset = new SET<Point2D>();
        addToSet(root, rect, subset);
        return subset;
    }
    
    private void addToSet(Node node, RectHV rect, SET<Point2D> set) {
        if (node == null) return;
        RectHV nodeRect = node.getRect();
        if (!nodeRect.intersects(rect)) return;
        if (rect.contains(node.p())) set.add(node.p());
        addToSet(node.getSubtree(true), rect, set);
        addToSet(node.getSubtree(false), rect, set);
    }
    
    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        Node node = findNearest(p, root, null, Double.POSITIVE_INFINITY);
        return node.p();
    }
    
    private Node findNearest(Point2D p, Node node, Node best, double curBest) {
        Node next = best;
        double bestDist = curBest;
        if (node == null) return null;
        double dist = node.p().distanceSquaredTo(p);
        if (dist < bestDist) {
            next = node;
            bestDist = dist;
        }
        Node lb = node.getSubtree(true);
        if (lb != null && lb.getRect().distanceSquaredTo(p) <= bestDist) {
            Node fromLb = findNearest(p, lb, next, bestDist);
            if (fromLb != null) {
                dist = fromLb.p().distanceSquaredTo(p);
                if (dist < bestDist) {
                    next = fromLb;
                    bestDist = dist;
                }
            }
        }
        Node rt = node.getSubtree(false);
        if (rt != null && rt.getRect().distanceSquaredTo(p) <= bestDist) {
            Node fromRt = findNearest(p, rt, next, bestDist);
            if (fromRt != null) {
                dist = fromRt.p().distanceSquaredTo(p);
                if (dist < bestDist) {
                    next = fromRt;
                }
            }
        }
        return next;
    }
    
    // test client
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        // initialize the data structures with N points from standard input
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        StdDraw.line(0, 0, 0, 1);
        StdDraw.line(0, 1, 1, 1);
        StdDraw.line(0, 0, 1, 0);
        StdDraw.line(1, 1, 1, 0);
        kdtree.draw();
        StdDraw.show();
    }
}