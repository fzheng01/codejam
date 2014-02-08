/**
 * @author Feng Zheng (summitzf@gmail.com)
 * @date 2014-02-05
 */
public class Percolation {
   private WeightedQuickUnionUF ufInstance;
   /**
    * create N-by-N grid, with all sites blocked
    */
   public Percolation(int N) {
       int sizeOfMatrix = N*N + 1;
       ufInstance = new WeightedQuickUnionUF(sizeOfMatrix);
   }
   /**
    * open site (row i, column j) if it is not already
    */
   public void open(int i, int j) {
   }
   /**
    * is site (row i, column j) open?
    */
   public boolean isOpen(int i, int j) {
       return false;
   }
   /**
    * is site (row i, column j) full?
    */
   public boolean isFull(int i, int j) {
       return false;
   }
   /**
    * does the system percolate?
    */
   public boolean percolates() {
       return false;
   }
}