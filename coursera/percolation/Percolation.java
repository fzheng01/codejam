/**
 * @author Feng Zheng (summitzf@gmail.com)
 * @date 2014-02-05
 */
public class Percolation {
   /**
    * using 2 weighted quick union
    * weightedUF with top and bottom virtual sites
    * weightedUFTop with only top virtual sites
    */
   private WeightedQuickUnionUF weightedUF;
   private WeightedQuickUnionUF weightedUFTop;
   private int sizeOfGrid;
   private int tailIndex;
   private boolean[] openGrid;
   /**
    * create N-by-N grid, with all sites blocked.
    */
   public Percolation(int N) {
       sizeOfGrid = N;
       tailIndex = N*N + 1;
       weightedUF = new WeightedQuickUnionUF(tailIndex + 1);
       weightedUFTop = new WeightedQuickUnionUF(tailIndex);
       openGrid = new boolean[tailIndex + 1];
       openGrid[0] = true;
       openGrid[tailIndex] = true;
   }
   
   /**
    * private function to convert 2D index to 1D index.
    */
   private int xyTo1D(int row, int col) {
       return sizeOfGrid*(row-1) + col;
   }
   
   /**
    * private function to verify input param.
    */
   private void verifyInput(int i, int j) {
       if (i <= 0 || i > sizeOfGrid) {
           throw new IndexOutOfBoundsException("row index i out of bounds");
       }
       if (j <= 0 || j > sizeOfGrid) {
           throw new IndexOutOfBoundsException("column index j out of bounds");
       }
   }
   
   /**
    * open site (row i, column j) if it is not already.
    */
   public void open(int i, int j) {
       verifyInput(i, j);
       int queryIndex = xyTo1D(i, j);
       if (!isOpen(i, j)) {
           openGrid[queryIndex] = true;
           // check left
           if (j > 1 && isOpen(i, j-1)) {
               weightedUF.union(queryIndex, queryIndex-1);
               weightedUFTop.union(queryIndex, queryIndex-1);
           }
           // check right
           if (j < sizeOfGrid && isOpen(i, j+1)) {
               weightedUF.union(queryIndex, queryIndex+1);
               weightedUFTop.union(queryIndex, queryIndex+1);
           }
           // check up
           if (i > 1 && isOpen(i-1, j)) {
               weightedUF.union(queryIndex, queryIndex-sizeOfGrid);
               weightedUFTop.union(queryIndex, queryIndex-sizeOfGrid);
           }
           // check down
           if (i < sizeOfGrid && isOpen(i+1, j)) {
               weightedUF.union(queryIndex, queryIndex+sizeOfGrid);
               weightedUFTop.union(queryIndex, queryIndex+sizeOfGrid);
           }
           // top border
           if (i == 1) {
               weightedUF.union(queryIndex, 0);
               weightedUFTop.union(queryIndex, 0);
           }
           // bottom border
           if (i == sizeOfGrid) {
               weightedUF.union(queryIndex, tailIndex);
           }
       }
   }
   
   /**
    * is site (row i, column j) open.
    */
   public boolean isOpen(int i, int j) {
       verifyInput(i, j);
       return openGrid[xyTo1D(i, j)];
   }
   
   /**
    * is site (row i, column j) full.
    */
   public boolean isFull(int i, int j) {
       verifyInput(i, j);
       return isOpen(i, j) && weightedUFTop.connected(xyTo1D(i, j), 0);
   }
   
   /**
    * does the system percolate.
    */
   public boolean percolates() {
       return weightedUF.connected(0, tailIndex);
   }
}