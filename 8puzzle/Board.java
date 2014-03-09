import java.util.Arrays;

public class Board {
    private final byte[][] blocks;
    private final int dim;
    
    // construct a board from an N by N array of blocks.
    // where blocks[i][j] = block in row i, column j.
    public Board(int[][] blocks) {
        dim = blocks.length;
        blocks = new byte[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                blocks[i][j] = (byte) blocks[i][j];
            }
        }
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
    
    private int getManhattanNumber(byte key, byte goalKey) {
        if (key == goalKey) return 0;
        return Math.abs(key/dim-goalKey/dim) + Math.abs(key%dim-goalKey%dim);
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
    }
    
    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }
    
    // a board obtained by exchanging two adjacent blocks in the same row.
    public Board twin() {
        // TODO
    }
    
    // does this board equal y?
    public boolean equals(Object y) {
    }
    
    // all neighboring boards.
    public Iterable<Board> neighbors() {
    }
    
    // string representation of the board.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}