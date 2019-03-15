/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Test {
    public static void main(String[] args) {


        int n = 3;
        int m = 1;
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m++;
                blocks[i][j] = m;
            }
        }

        Board initial = new Board(blocks);

        // Board board = new Board(blocks);
        // board.toString();
    }
}
