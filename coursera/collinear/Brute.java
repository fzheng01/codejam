import java.util.Arrays;

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
        int i, p, q, r, s;
        double slopePQ, slopePR, slopePS;
        for (i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        Arrays.sort(points);
        for (p = 0; p < N-3; p++) {
            for (q = p+1; q < N-2; q++) {
                slopePQ = points[p].slopeTo(points[q]);
                for (r = q+1; r < N-1; r++) {
                    slopePR = points[p].slopeTo(points[r]);
                    if (slopePQ != slopePR) {
                        continue;
                    }
                    for (s = r+1; s < N; s++) {
                        slopePS = points[p].slopeTo(points[s]);
                        if (slopePQ == slopePS) {
                            StringBuffer output;
                            output = new StringBuffer(points[p].toString());
                            output.append(" -> ");
                            output.append(points[q].toString());
                            output.append(" -> ");
                            output.append(points[r].toString());
                            output.append(" -> ");
                            output.append(points[s].toString());
                            StdOut.println(output.toString());
                            points[p].drawTo(points[s]);
                        }
                    }
                }
            }
        }
        // display to screen all at once
        StdDraw.show(0);
    }
}