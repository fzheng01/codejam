public class Board {
    private final byte[][] blocks;
    private final int dim;
    private final int[] blankXY;
    
    // construct a board from an N by N array of blocks.
    // where blocks[i][j] = block in row i, column j.
    public Board(int[][] blocks) {
        dim = blocks.length;
        this.blocks = new byte[dim][dim];
        int x = 0, y = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                this.blocks[i][j] = (byte) blocks[i][j];
                if (blocks[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        blankXY = new int[]{x, y};
    }
    
    // board dimension N.
    public int dimension() {
        return dim;
    }
    
    // number of blocks out of place.
    public int hamming() {
        int hnum = 0;
        byte goalKey = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                goalKey++;
                if (blocks[i][j] == 0) continue;
                if (blocks[i][j] != goalKey) hnum++;
            }
        }
        return hnum;
    }
    
    // private function to assist getting manhattan number
    private int getManhattanNumber(byte key, byte goalKey) {
        if (key-- == goalKey--) return 0;
        return Math.abs(key/dim - goalKey/dim) + Math.abs(key % dim - goalKey % dim);
    }
    
    // sum of Manhattan distances between blocks and goal.
    public int manhattan() {
        int mnum = 0;
        byte goalKey = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                goalKey++;
                if (blocks[i][j] == 0) continue;
                mnum += getManhattanNumber(blocks[i][j], goalKey);
            }
        }
        return mnum;
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }
    
    // a board obtained by exchanging two adjacent blocks in the same row.
    public Board twin() {
        int[][] twinBlocks = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                twinBlocks[i][j] = blocks[i][j];
            }
        }
        if (dim > 1) {
            int row = dim-1;
            if (blankXY[0] == dim-1) row = dim-2;
            int temp = twinBlocks[row][dim-1];
            twinBlocks[row][dim-1] = twinBlocks[row][dim-2];
            twinBlocks[row][dim-2] = temp;
        }
        return new Board(twinBlocks);
    }
    
    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.dim != this.dim) return false;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
                if (that.blocks[i][j] != this.blocks[i][j]) return false;
        return true;
    }
    
    // all neighboring boards.
    public Iterable<Board> neighbors() {
        Queue<Board> nbQueue = new Queue<Board>();
        int[][] midBlocks = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                midBlocks[i][j] = blocks[i][j];
            }
        }
        int x = blankXY[0], y = blankXY[1];
        if (x > 0) {
            midBlocks[x][y] = midBlocks[x-1][y];
            midBlocks[x-1][y] = 0;
            nbQueue.enqueue(new Board(midBlocks));
            midBlocks[x-1][y] = midBlocks[x][y];
            midBlocks[x][y] = 0;
        }
        if (x < dim-1) {
            midBlocks[x][y] = midBlocks[x+1][y];
            midBlocks[x+1][y] = 0;
            nbQueue.enqueue(new Board(midBlocks));
            midBlocks[x+1][y] = midBlocks[x][y];
            midBlocks[x][y] = 0;
        }
        if (y > 0) {
            midBlocks[x][y] = midBlocks[x][y-1];
            midBlocks[x][y-1] = 0;
            nbQueue.enqueue(new Board(midBlocks));
            midBlocks[x][y-1] = midBlocks[x][y];
            midBlocks[x][y] = 0;
        }
        if (y < dim-1) {
            midBlocks[x][y] = midBlocks[x][y+1];
            midBlocks[x][y+1] = 0;
            nbQueue.enqueue(new Board(midBlocks));
        }
        return nbQueue;
    }
    
    // string representation of the board.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dim + "\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}