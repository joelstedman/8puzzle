import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Solver {
    private int moves = -1;
    private boolean solvable;
    private List<Board> finalList;

    private class SearchNode {
        Board board;
        SearchNode prev;
        int moves;
        int hamming;
        boolean solvable;

        public SearchNode() {

        }

        public SearchNode(Board board, SearchNode prev, int moves) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            this.hamming = board.hamming() + moves;
        }
    }

    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        MinPQ<SearchNode> q = new MinPQ<SearchNode>(new compareNodes());
        //insert initial board
        q.insert(new SearchNode(initial, null, 0));

        //create temps
        Board board;
        SearchNode prev = null;
        Iterable<Board> neighbors;
        Iterator<Board> it;
        SearchNode popNode = new SearchNode();
        List<Board> list = new ArrayList<Board>();

        int times = 20;
        boolean goalBoard = false;


        while (!goalBoard) {
            //pop off q
            popNode = q.delMin();
            board = popNode.board;
            goalBoard = board.isGoal();
            int moves = 3;

            //get the neighbors
            neighbors = board.neighbors();
            it = neighbors.iterator();
            while (it.hasNext()) {
                board = it.next();
                q.insert(new SearchNode(board, prev, this.moves));
            }
            prev = popNode;
            list.add(board);

            this.moves++;


            if (this.moves == times) {
                break;
            }
        }
        this.finalList = list;
    }


    private class compareNodes implements Comparator<SearchNode> {
        public compareNodes() {

        }

        public int compare(SearchNode node, SearchNode anotherNode) {
            return node.hamming - anotherNode.hamming;
        }
    }

    public boolean isSolvable() {// is the initial board solvable?
        this.solvable = true;
        //change this to false if twin hits goal
        return this.solvable;
    }

    public int moves() {// min number of moves to solve initial board; -1 if unsolvable
        //change this to -1 if twin hits goal
        return this.moves;
    }

    public Iterable<Board> solution() { // sequence of boards in a shortest solution; null if unsolvable
        Iterable<Board> iterable = new IterableBoards(this.finalList);
        return iterable;
    }

    private class IterableBoards implements Iterable<Board> {
        List<Board> list;

        private IterableBoards(List<Board> list) {
            this.list = list;
        }

        public Iterator<Board> iterator() {
            return list.iterator();
        }

    }

    public static void main(String[] args) {
        //create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int m;
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m = in.readInt();
                blocks[i][j] = m;
            }
        }

        Board initial = new Board(blocks);

        //solve the puzzle
        Solver solver = new Solver(initial);
        Iterable<Board> boards = solver.solution();
        Iterator<Board> it = boards.iterator();
        while (it.hasNext()) {
            Board board = it.next();
            System.out.println(board.toString());
        }


        // print solution to standard output
        // if (!solver.isSolvable())
        //     StdOut.println("No solution possible");
        // else {
        //     StdOut.println("Minimum number of moves = " + solver.moves());
        //     for (Board board : solver.solution())
        //         StdOut.println(board);


        //create board with different hamming to test comparator
        // int n = 3;
        // int m = (n * n) - 1;
        // int[][] blocks = new int[n][n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         blocks[i][j] = m;
        //         m--;
        //     }
        // }
        // Board board3 = new Board(blocks);
        // //System.out.println(board3.toString());
        // n = 4;
        // m = (n * n) - 1;
        // blocks = new int[n][n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         blocks[i][j] = m;
        //         m--;
        //     }
        // }
        // Board board4 = new Board(blocks);
        // //System.out.println(board4.toString());
        // n = 5;
        // m = (n * n) - 1;
        // blocks = new int[n][n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         blocks[i][j] = m;
        //         m--;
        //     }
        // }
        // Board board5 = new Board(blocks);
        // System.out.println(board5.toString());


    }
}

