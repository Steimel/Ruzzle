

package ruzzlesolver;


public class RuzzleSolution implements Comparable
{
    String word;
    int points;
    boolean[][] used;

    public RuzzleSolution()
    {
        word = "";
        points = 0;
        used = new boolean[RuzzleBoard.NUM_ROWS][RuzzleBoard.NUM_COLS];
        for(int r = 0; r < RuzzleBoard.NUM_ROWS; r++)
        {
            for(int c = 0; c < RuzzleBoard.NUM_COLS; c++)
            {
                used[r][c] = false;
            }
        }
    }

    public RuzzleSolution(String w, int p)
    {
        word = w;
        points = p;
        used = new boolean[RuzzleBoard.NUM_ROWS][RuzzleBoard.NUM_COLS];
        for(int r = 0; r < RuzzleBoard.NUM_ROWS; r++)
        {
            for(int c = 0; c < RuzzleBoard.NUM_COLS; c++)
            {
                used[r][c] = false;
            }
        }
    }

    public RuzzleSolution(RuzzleSolution copy)
    {
        word = new String(copy.word);
        points = copy.points;
        used = new boolean[RuzzleBoard.NUM_ROWS][RuzzleBoard.NUM_COLS];
        for(int r = 0; r < RuzzleBoard.NUM_ROWS; r++)
        {
            for(int c = 0; c < RuzzleBoard.NUM_COLS; c++)
            {
                used[r][c] = copy.used[r][c];
            }
        }
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof RuzzleSolution)) return 0;
        RuzzleSolution r = (RuzzleSolution)o;
        return points - r.points;
    }

    public String toString()
    {
        return word;
    }
}
