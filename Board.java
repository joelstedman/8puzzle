/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;


class Board {
    private int n;
    private int[][] boardArr;
    private int[][] twinBoardArr;
    private int hamming;

    public Board(int[][] block) {
        this.boardArr = block;
        this.n = block.length;
        this.twinBoardArr = new int[this.n][this.n];
        this.hamming = this.hamming();

    }


    public int dimension() {
        return this.n;
    }

    public int hamming() {
        int goalN = 1;
        int hammingCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int startValue = this.boardArr[i][j];
                this.twinBoardArr[i][j] = startValue;
                // System.out.println("start:" + startValue + "*");
                // System.out.println("n:" + goalN + "*");
                if (i == n - 1 && j == n - 1) {
                    goalN = 0;
                }
                if (startValue != goalN) {
                    hammingCount++;
                }
                goalN++;
            }
        }
        return hammingCount;
    }

    public int manhattan() {// sum of Manhattan distances between blocks and goal
        //Recall that the blank square is not considered a block
        return 10;
        //https://codereview.stackexchange.com/questions/86597/optimizing-manhattan-distance-method-for-n-by-n-puzzles
    }


    public boolean isGoal() {//is this board the goal board?
        int goalN = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int startValue = this.boardArr[i][j];
                // System.out.println("start:" + startValue + "*");
                // System.out.println("n:" + goalN + "*");
                if (i == n - 1 && j == n - 1) {
                    goalN = 0;
                }
                if (startValue != goalN) {
                    return false;
                }
                goalN++;
            }
        }
        return true;
    }

    public Board twin() {// a board that is obtained by exchanging any pair of blocks
        boolean found1 = false;
        boolean found2 = false;
        int val = 0;
        int i1 = 0;
        int j1 = 0;
        int i2 = 0;
        int j2 = 0;
        int val1 = 0;
        int val2 = 0;
        //loop through
        //find first two that aren't zero
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = this.twinBoardArr[i][j];
                if (val != 0) {
                    if (!found1) {
                        i1 = i;
                        j1 = j;
                        val1 = val;

                        found1 = true;
                    }
                    else if (!found2) {
                        i2 = i;
                        j2 = j;
                        val2 = val;
                        found2 = true;
                        break;
                    }
                }
            }
            break;
        }
        this.twinBoardArr[i1][j1] = val2;
        this.twinBoardArr[i2][j2] = val1;

        Board board = new Board(this.twinBoardArr);
        return board;
    }

    //
    public boolean equals(Object y) {// does this board equal y?
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        String strY = y.toString();
        String strX = this.toString();
        int len = strY.length();
        for (int i = 0; i < len; i++) {
            if (strY.charAt(i) != strX.charAt(i)) return false;
        }
        return true;
    }

    //
    // public Iterable<Board> neighbors() {//check all neighbors against the previous
    //
    // }   // all neighboring boards
    //
    public String toString() {// string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", this.boardArr[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }


    public static void main(String[] args) {


        //create initial board from file

        int n = 3;
        int m = 0;
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m++;
                blocks[i][j] = m;
            }
        }
        //
        Board initial = new Board(blocks);
        Board initialCopy = new Board(blocks);
        Board twin = initial.twin();
        StdOut.println(initial);
        StdOut.println(twin);
        StdOut.println(initial.equals(initialCopy));
        StdOut.println(initial.equals(twin));
        // //solve the puzzle
        // Solver solver = new Solver(initial);
        //
        // // print solution to standard output
        // if (!solver.isSolvable())
        //     StdOut.println("No solution possible");
        // else {
        //     StdOut.println("Minimum number of moves = " + solver.moves());
        //     for (Board board : solver.solution())
        //         StdOut.println(board);


    }

}
