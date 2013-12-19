

package ruzzlesolver;

import java.util.ArrayList;
import java.util.Collections;


public class RuzzleSolver
{
    private RuzzleBoard board;
    private Dictionary dictionary;

    public RuzzleSolver(RuzzleBoard b, Dictionary d)
    {
        board = b;
        dictionary = d;
    }

    public ArrayList<RuzzleSolution> solve()
    {
        ArrayList<RuzzleSolution> solution = new ArrayList<RuzzleSolution>();

        for(int startRow = 0; startRow < RuzzleBoard.NUM_ROWS; startRow++)
        {
            for(int startCol = 0; startCol < RuzzleBoard.NUM_COLS; startCol++)
            {
                ArrayList<RuzzleSolution> currentSolutions = new ArrayList<RuzzleSolution>();
                findSolutions(startRow,startCol,new RuzzleSolution(),currentSolutions);
                solution.addAll(currentSolutions);
            }
        }

        Collections.sort(solution);
        ArrayList<RuzzleSolution> finalSolutionList = new ArrayList<RuzzleSolution>();
        for(int x = solution.size()-1; x >= 0; x--)
        {
            RuzzleSolution current = solution.get(x);
            if(!hasSolution(finalSolutionList,current)) finalSolutionList.add(current);
        }
        return finalSolutionList;
    }

    private boolean hasSolution(ArrayList<RuzzleSolution> list, RuzzleSolution rs)
    {
        for(int x = 0; x < list.size(); x++)
        {
            if(list.get(x).word.equals(rs.word)) return true;
        }
        return false;
    }

    private void findSolutions(int row,
                                int col,
                                RuzzleSolution current,
                                ArrayList<RuzzleSolution> currentSolutions)
    {
        if(row < 0 || row >= RuzzleBoard.NUM_ROWS) return;
        if(col < 0 || col >= RuzzleBoard.NUM_COLS) return;
        if(current.used[row][col]) return;
        current.word += board.get(row, col).letter;
        current.points += board.get(row, col).points;
        // length bonus
        if(current.word.length() > 4) current.points += 5;
        current.used[row][col] = true;
        if(dictionary.isWord(current.word))
        {
            currentSolutions.add(new RuzzleSolution(current));
        }
        if(dictionary.isPrefix(current.word))
        {
            findSolutions(row-1, col-1, new RuzzleSolution(current), currentSolutions);
            findSolutions(row-1, col, new RuzzleSolution(current), currentSolutions);
            findSolutions(row-1, col+1, new RuzzleSolution(current), currentSolutions);
            findSolutions(row, col-1, new RuzzleSolution(current), currentSolutions);
            findSolutions(row, col+1, new RuzzleSolution(current), currentSolutions);
            findSolutions(row+1, col-1, new RuzzleSolution(current), currentSolutions);
            findSolutions(row+1, col, new RuzzleSolution(current), currentSolutions);
            findSolutions(row+1, col+1, new RuzzleSolution(current), currentSolutions);
        }
    }

}
