/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
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
        Board twin = initial.twin();
        StdOut.println("String Initial:" + initial.toString());
        StdOut.println("String Twin:" + twin.toString());


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
