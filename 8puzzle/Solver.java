public class Solver {
    private BoardElement result;
    
    // inner class BoardElement
    private class BoardElement implements Comparable<BoardElement> {
        private final Board board;
        private final int move;
        private final BoardElement last;
        private final int priority;
        private boolean isSolution;
        BoardElement(Board currentBoard, BoardElement lastElement) {
            board = currentBoard;
            last = lastElement;
            if (lastElement == null) move = 0;
            else move = lastElement.move + 1;
            priority = currentBoard.manhattan() + move;
        }
        public int compareTo(BoardElement that) {
            return priority - that.priority;
        }
    }
    
    // find a solution to the initial board.
    // using A* algorithm.
    public Solver(Board initial) {
        MinPQ<BoardElement> pqSelf = new MinPQ<BoardElement>();
        MinPQ<BoardElement> pqTwin = new MinPQ<BoardElement>();
        pqSelf.insert(new BoardElement(initial, null));
        pqTwin.insert(new BoardElement(initial.twin(), null));
        BoardElement mid;
        do {
            mid = processPQ(pqSelf);
            BoardElement tmp = processPQ(pqTwin);
            if (tmp.isSolution) {
                break;
            }
        } while(!mid.isSolution);
        if (mid.isSolution) {
            this.result = mid;
        }
    }
    
    private BoardElement processPQ(MinPQ<BoardElement> pg) {
        BoardElement min = pg.delMin();
        if (min.board.isGoal()) min.isSolution = true;
        else {
            Iterable<Board> iterate = min.board.neighbors();
            for (Board b : iterate) {
                if (b != null && (min.last == null || b != min.last.board)) {
                    pg.insert(new BoardElement(b, min));
                }
            }
        }
        return min;
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {
        return result != null;
    }
    
    // min number of moves to solve initial board.
    // -1 if no solution
    public int moves() {
        if (isSolvable()) return result.move;
        else return -1;
    }
    
    // sequence of boards in a shortest solution.
    // null if no solution.
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> boardStack = new Stack<Board>();
        BoardElement current = this.result;
        while (current != null) {
            boardStack.push(current.board);
            current = current.last;
        }
        return boardStack;
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