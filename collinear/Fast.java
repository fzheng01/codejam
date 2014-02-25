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
        int i, j, k, q, r;
        for (i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            pointsOrg[i] = new Point(x, y);
            pointsOrg[i].draw();
        }
        Arrays.sort(pointsOrg);
        for (i = 0; i < N; i++) {
            for (j = 1; j < N; j++) {
                points[j] = pointsOrg[j];
            }
            points[0] = pointsOrg[i];
            points[i] = pointsOrg[0];
            q = 1;
            Arrays.sort(points, q, N, points[0].SLOPE_ORDER);
            while (q < N-2) {
                if (points[0].compareTo(points[q]) >= 0) {
                    q++;
                    continue;
                }
                double slopeQ = points[0].slopeTo(points[q]);
                r = q + 1;
                while (r < N) {
                    double slopeR = points[0].slopeTo(points[r]);
                    if (slopeQ != slopeR) {
                        break;
                    } else {
                        r++;
                    }
                }
                r--;
                if (r-q >= 2) {
                    StringBuffer output = new StringBuffer(points[0].toString());
                    output.append(" -> ");
                    for (k = q; k < r; k++) {
                        output.append(points[k].toString());
                        output.append(" -> ");
                    }
                    output.append(points[r].toString());
                    StdOut.println(output.toString());
                    points[0].drawTo(points[r]);
                }
                r++;
                q = r;
            }
        }
        // display to screen all at once
        StdDraw.show(0);
    }
}