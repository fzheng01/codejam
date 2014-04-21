import java.util.*;
import java.io.*;

/**
 * start from top left
 * if it is single free cell, click it
 * if it is single row or column, click top left
 * 
 * minimum solvable cell is 2x2, so all of its expansions are solvable
 * example:
 * c....**
 * .....**
 * ..???**
 * ..???**
 * ..???**
 * *******
 * 
 * no mine in '.' cells, otherwise, unsovlable
 * '?' cells are optional
 * 
 * find the rmax and cmax, so that free cells amount N satisfies the inequation
 * 2*(rmax + cmax) <= N <= (rmax+1) * (cmax+1)
 * 
 */
public class MinesweeperMaster {
    private final boolean[][] board;
    private boolean solvable;
    private final int row;
    private final int col;
    private final int safe;
    public MinesweeperMaster(int r, int c, int m) {
        row = r;
        col = c;
        safe = row*col - m;
        board = new boolean[row][col];
        board[0][0] = true;
        int counter = safe;
        if (safe == 1) solvable = true;
        else if (row == 1 || col == 1) {
            solvable = true;
            LOOP1:
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    board[i][j] = true;
                    if (--counter <= 0) break LOOP1;
                }
            }
        }
        else if (safe == 3 || safe == 5) solvable = false;
        else {
            solvable = false;
            LOOP3:
            for (int rmax = 1; rmax < row; rmax++) {
                for (int cmax = 1; cmax < col; cmax++) {
                    if (safe >= 2*(rmax+cmax) && safe <= (rmax+1)*(cmax+1)) {
                        solvable = true;
                        counter = safe - 2*(rmax+cmax);
                        int i, j;
                        for (i = 0; i <= 1; i++) {
                            for (j = 0; j <= cmax; j++) {
                                board[i][j] = true;
                            }
                        }
                        for (i = 0; i <= rmax; i++) {
                            for (j = 0; j <= 1; j++) {
                                board[i][j] = true;
                            }
                        }
                        LOOP2:
                        for (i = 2; i <= rmax; i++) {
                            for (j = 2; j <= cmax; j++) {
                                if (counter-- <= 0) break LOOP2;
                                board[i][j] = true;
                            }
                        }
                        break LOOP3;
                    }
                }
            }
        }
    }
    
    public String toString() {
        if (!solvable) return "Impossible";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) str.append("c");
                else if (board[i][j]) str.append(".");
                else str.append("*");
            }
            if (i < row-1) str.append("\n");
        }
        return str.toString();
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            throw new IllegalArgumentException("Require input file name");
        Scanner sc = new Scanner(new FileReader(args[0]));
        String outFilename = args[0].replaceFirst("[.][^.]+$", "").concat(".out");
        PrintWriter pw = new PrintWriter(new FileWriter(outFilename));
        int caseCnt = sc.nextInt();
        int dim = 4;
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.println("Case #" + (caseNum + 1) + ":");
            int R = sc.nextInt();
            int C = sc.nextInt();
            int M = sc.nextInt();
            MinesweeperMaster mm = new MinesweeperMaster(R, C, M);
            pw.println(mm);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}