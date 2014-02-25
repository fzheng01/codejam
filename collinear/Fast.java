import java.util.Arrays;

public class Fast {
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
        Point[] pointsOrg = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            pointsOrg[i] = new Point(x, y);
            pointsOrg[i].draw();
        }
        Arrays.sort(pointsOrg);
        for (int p = 0; p < N-3; p++) {
            for (int i = 0; i < N; i++) {
                points[i] = pointsOrg[i];
            }
            int q = p + 1;
            Arrays.sort(points, q, N, points[p].SLOPE_ORDER);
            while (q < N-2) {
                double slopeQ = points[p].slopeTo(points[q]);
                int r = q + 1;
                while (r < N) {
                    double slopeR = points[p].slopeTo(points[r]);
                    if (slopeQ != slopeR) {
                        break;
                    } else {
                        r++;
                    }
                }
                r--;
                if (r-q >= 2) {
                    StringBuffer output = new StringBuffer(points[p].toString());
                    output.append(" -> ");
                    for (int i = q; i < r; i++) {
                        output.append(points[i].toString());
                        output.append(" -> ");
                    }
                    output.append(points[r].toString());
                    StdOut.println(output.toString());
                    points[p].drawTo(points[r]);
                }
                r++;
                q = r;
            }
        }
        // display to screen all at once
        StdDraw.show(0);
    }
}