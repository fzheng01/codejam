public class Solver {
    private BoardElement answer;
    
    // inner class BoardElement
    private class BoardElement implements Comparable<Board> {
        private final Board board;
        private final int move;
        private final BoardElement last;
        private final int priority;
        BoardElement(Board currentBoard, BoardElement lastElement) {
            board = currentBoard;
            last = lastElement;
            if (lastElement == null) move = 0;
            else move = ++lastElement.move;
            priority = currentBoard.manhattan() + move;
        }
        public int compareTo(BoardElement that) {
            return priority - that.priority;
        }
    }
    
    // find a solution to the initial board.
    // using A* algorithm.
    public Solver(Board initial) {
        
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {
    }
    
    // min number of moves to solve initial board.
    // -1 if no solution
    public int moves() {

    }
    
    // sequence of boards in a shortest solution.
    // null if no solution.
    public Iterable<Board> solution() {
    }
    
    // solve a slider puzzle
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}