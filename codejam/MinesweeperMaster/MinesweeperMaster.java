import java.util.*;
import java.io.*;

// have issue on 3rd scenario
public class MinesweeperMaster {
    private final int[][] board;
    private boolean solvable = true;
    private final int row;
    private final int col;
    public MinesweeperMaster(int r, int c, int m) {
        row = r;
        col = c;
        board = new int[r][c];
        int mines = m, i = 0, j = 0;
        boolean rt = true, lf = false, up = false, dn = false;
        while (mines-- > 0) {
            if (rt) {
                board[i][j++] = -1;
                if (j >= col || board[i][j] < 0) {
                    rt = false;
                    dn = true;
                    i++;
                    j--;
                }
            } else if (lf) {
                board[i][j--] = -1;
                if (j < 0 || board[i][j] < 0) {
                    lf = false;
                    up = true;
                    i--;
                    j++;
                }
            } else if (up) {
                board[i--][j] = -1;
                if (i < 0 || board[i][j] < 0) {
                    up = false;
                    rt = true;
                    j++;
                    i++;
                }
            } else {
                board[i++][j] = -1;
                if (i >= row || board[i][j] < 0) {
                    dn = false;
                    lf = true;
                    j--;
                    i--;
                }
            }
        }
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                if (board[i][j] < 0) continue;
                else board[i][j] = assignType(i, j);
            }
        }
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                if (board[i][j] == 1 && !evaluate(i, j)) {
                    solvable = false;
                    break;
                }
            }
        }
    }
    
    private int assignType(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int xx = x + i, yy = y + j;
                if (xx < 0 || xx >= row) continue;
                if (yy < 0 || yy >= col) continue;
                if (board[xx][yy] < 0) return 1;
            }
        }
        return 0;
    }
    
    private boolean evaluate(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int xx = x + i, yy = y + j;
                if (xx < 0 || xx >= row) continue;
                if (yy < 0 || yy >= col) continue;
                if (board[xx][yy] == 0) return true;
            }
        }
        return false;
    }
    
    public String toString() {
//        if (!solvable) return "Impossible";
        boolean didClick = false;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] < 0) str.append("*");
                else if (board[i][j] == 0 && !didClick) {
                    str.append("c");
                    didClick = true;
                } else str.append(".");
            }
            if (i < row-1) str.append("\n");
        }
        return str.toString();
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new IllegalArgumentException("Require input and output filenames");
        Scanner sc = new Scanner(new FileReader(args[0]));
        PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
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