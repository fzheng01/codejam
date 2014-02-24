/*************************************************************************
 * Name: Feng Zheng
 * Email: summitzf@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else if (this.y == that.y) {
            return 0.0;
        } else {
            return 0.1*(that.y - this.y)/(that.x - this.x);
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else {
            if (this.x < that.x) {
                return -1;
            } else if (this.x > that.x) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    // compare points by slope to this point
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            if (slope1 < slope2) {
                return -1;
            } else if (slope1 > slope2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, 1);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(2, 5);
        assert p0.slopeTo(p1) == Double.POSITIVE_INFINITY;
        assert p0.slopeTo(p2) == 0.0;
        assert p0.slopeTo(p0) == Double.NEGATIVE_INFINITY;
        assert p0.slopeTo(p3) < p0.slopeTo(p4);
        assert p3.compareTo(p4) == -1;
        assert p3.compareTo(p2) == 1;
        assert p3.compareTo(p1) == 1;
    }
}
