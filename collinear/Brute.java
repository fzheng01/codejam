public class Brute {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        int i, j, k, l;
        for (i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        // display to screen all at once
        StdDraw.show(0);
        for (i = 0; i < N-3; i++) {
            for (j = i+1; j < N-2; j++) {
                for (k = j+1; k < N-1; k++) {
                    for (l = k+1; l < N; l++) {
                    }
                }
            }
        }
    }
}