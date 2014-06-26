public class PointSET {
    private SET<Point2D> set;
    
    // construct an empty set of points
    public PointSET() {
        this.set = new SET<Point2D>();
    }
    
    // is the set empty ?
    public boolean isEmpty() {
        return set.isEmpty();
    }
    
    // number of points in the set
    public int size() {
        return set.size();
    }
    
    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (!this.contains(p)) set.add(p);
    }
    
    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return set.contains(p);
    }
    
    // draw all of the points to standard draw
    public void draw() {
        for (Point2D p : set)
            p.draw();
    }
    
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> subset = new SET<Point2D>();
        for (Point2D p : set) {
            if (rect.contains(p))
                subset.add(p);
        }
        return subset;
    }
    
    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        Point2D result = null;
        double dist = Double.POSITIVE_INFINITY;
        for (Point2D p1 : set) {
            double newDist = p1.distanceSquaredTo(p);
            if (newDist < dist) {
                dist = newDist;
                result = p1;
            }
        }
        return result;
    }
}