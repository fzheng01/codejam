public class Islands {
    private int count;
    private final int ROW;
    private final int COL;
    private boolean[][] visited;
    private int[][] ids;
    
    Islands(int[][] graph) {
        this.count = 0;
        this.ROW = graph.length;
        if (this.ROW < 1) throw new IllegalArgumentException();
        this.COL = graph[0].length;
        this.visited = new boolean[ROW][COL];
        this.ids = new int[ROW][COL];
        
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    count++;
                    dfs(graph, i, j);
                }
            }
        }
    }
    
    private void dfs(int[][] graph, int x, int y) {
        visited[x][y] = true;
        ids[x][y] = count;
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i < 0 || i >= ROW || j < 0 || j >= COL || (i == x && j == y)) continue;
                if (graph[i][j] == 1 && !visited[i][j]) {
                    dfs(graph, i, j);
                }
            }
        }
    }
    
    public int count() {
        return this.count;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                str.append(ids[i][j]);
                if (j == COL-1) str.append("\n");
                else str.append(", ");
            }
        }
        return str.toString();
    }
    
    public static void main(String[] args) {
        int[][] M = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
        };
        Islands land = new Islands(M);
        System.out.println(land);
        System.out.println(land.count());
    }
}