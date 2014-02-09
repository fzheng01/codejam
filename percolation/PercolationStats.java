/**
 * @author Feng Zheng (summitzf@gmail.com)
 * @date 2014-02-05
 */
public class PercolationStats {
    private double[] thresholds;
    private int iterations;
    
    /**
     * perform T independent computational experiments on an N-by-N grid.
     */
    public PercolationStats(int N, int T) {
        if (N <= 0) {
            throw new IllegalArgumentException("illegal grid size N");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("illegal computational times T");
        }
        iterations = T;
        int row;
        int col;
        int i = 0;
        thresholds = new double[T];
        // start experiment by repeating T times
        do {
            Percolation pl = new Percolation(N);
            int step = 0;
            // open sites until it is percolated
            do {
                // choose a site uniformly at random among all blocked sites
                do {
                    row = StdRandom.uniform(1, N+1);
                    col = StdRandom.uniform(1, N+1);
                } while (pl.isOpen(row, col));
                pl.open(row, col);
                step++;
            } while (!pl.percolates());
            thresholds[i] = 1.0 * step/(N*N);
        } while (++i < T);
    }
    /**
     * sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }
    /**
     * sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    /**
     * returns lower bound of the 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(iterations);
    }
    /**
     * returns upper bound of the 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt(iterations);
    }
    /**
     * test client.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Two command line args required");
        }
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(N, T);
        StdOut.print("mean                    = ");
        StdOut.println(percolationStats.mean());
        StdOut.print("stddev                  = ");
        StdOut.println(percolationStats.stddev());
        StdOut.print("95% confidence interval = ");
        StdOut.print(percolationStats.confidenceLo());
        StdOut.print(", ");
        StdOut.println(percolationStats.confidenceHi());
    }
}