
package ruzzlesolver;


public class RuzzleBoard
{
    private RuzzlePiece[][] board;
    public static final int NUM_ROWS = 4;
    public static final int NUM_COLS = 4;

    public RuzzleBoard()
    {
        board = new RuzzlePiece[NUM_ROWS][NUM_COLS];
    }

    public void set(int row, int col, String letter)
    {
        board[row][col] = new RuzzlePiece(letter);
    }

    public RuzzlePiece get(int row, int col)
    {
        return board[row][col];
    }
}
