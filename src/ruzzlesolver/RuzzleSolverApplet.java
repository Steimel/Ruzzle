
package ruzzlesolver;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class RuzzleSolverApplet extends JApplet {
    private JPanel[][] boardPanels;
    private JTextField[][] guiBoard;
    private JButton btnSolve;
    private JButton btnClear;
    private JPanel pnlBoard;
    private JPanel pnlButton;
    private JPanel pnlBoardAndSolution;
    private JScrollPane pnlScroll;
    private JList lstSolution;
    private Dictionary dictionary;
    private RuzzleBoard board;
    private RuzzleSolution[] solution;
    private boolean solved;

    public void init()
    {
        // Set up dictionary and board
        dictionary = new Dictionary();
        board = new RuzzleBoard();
        solved = false;

        // Set up GUI elements
        pnlBoard = new JPanel();
        pnlBoard.setLayout(new GridLayout(0,RuzzleBoard.NUM_COLS));
        boardPanels = new JPanel[RuzzleBoard.NUM_ROWS][RuzzleBoard.NUM_COLS];
        guiBoard = new JTextField[RuzzleBoard.NUM_ROWS][RuzzleBoard.NUM_COLS];
        for(int r = 0; r < RuzzleBoard.NUM_ROWS; r++)
        {
            for(int c = 0; c < RuzzleBoard.NUM_COLS; c++)
            {
                boardPanels[r][c] = new JPanel();
                boardPanels[r][c].setLayout(new GridLayout(0,3));
                guiBoard[r][c] = new JTextField();
                guiBoard[r][c].getDocument().addDocumentListener(new GuiBoardListener(r,c));
                for(int x = 0; x < 4; x++) boardPanels[r][c].add(new JLabel(""));
                boardPanels[r][c].add(guiBoard[r][c]);
                for(int x = 0; x < 4; x++) boardPanels[r][c].add(new JLabel(""));
                boardPanels[r][c].setBorder(new LineBorder(Color.BLACK,1,true));
                boardPanels[r][c].setBackground(Color.gray);
                pnlBoard.add(boardPanels[r][c]);
            }
        }
        guiBoard[RuzzleBoard.NUM_ROWS-1][RuzzleBoard.NUM_COLS-1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                btnSolve.requestFocus();
                solve();
            }
        });

        pnlButton = new JPanel();
        pnlButton.setLayout(new GridLayout(0,4));
        btnSolve = new JButton("Solve");
        btnSolve.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                solve();
            }
        });
        btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                clear();
            }
        });
        for(int x = 0; x < 5; x++) pnlButton.add(new JLabel(""));
        pnlButton.add(btnSolve);
        pnlButton.add(btnClear);
        for(int x = 0; x < 5; x++) pnlButton.add(new JLabel(""));
       
        lstSolution = new JList();
        lstSolution.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e)
            {
                selectionChanged();
            }
        });
        pnlScroll = new JScrollPane(lstSolution);

        pnlBoardAndSolution = new JPanel();
        pnlBoardAndSolution.setLayout(new GridLayout(0,2));
        pnlBoardAndSolution.add(pnlBoard);
        pnlBoardAndSolution.add(pnlScroll);

        this.setLayout(new GridLayout(0,1));
        this.setSize(500, 500);
        this.add(pnlBoardAndSolution);
        this.add(pnlButton);
    }

    private void solve()
    {
        solved = false;
        for(int r = 0; r < RuzzleBoard.NUM_ROWS; r++)
        {
            for(int c = 0; c < RuzzleBoard.NUM_COLS; c++)
            {
                board.set(r, c, guiBoard[r][c].getText().trim().toLowerCase());
            }
        }
        RuzzleSolver solver = new RuzzleSolver(board, dictionary);
        ArrayList<RuzzleSolution> solutionList = solver.solve();
        solution = new RuzzleSolution[solutionList.size()];
        for(int x = 0; x < solutionList.size(); x++)
        {
            solution[x] = solutionList.get(x);
        }
        lstSolution.setListData(solution);
        solved = true;
    }

    private void selectionChanged()
    {
        if(!solved) return;
        RuzzleSolution selectedSolution = (RuzzleSolution)lstSolution.getSelectedValue();
        for(int r = 0; r < RuzzleBoard.NUM_ROWS; r++)
        {
            for(int c = 0; c < RuzzleBoard.NUM_COLS; c++)
            {
                if(selectedSolution.used[r][c])
                {
                    boardPanels[r][c].setBackground(Color.CYAN);
                }
                else
                {
                    boardPanels[r][c].setBackground(Color.gray);
                }
            }
        }
    }

    private void clear()
    {
        for(int r = 0; r < RuzzleBoard.NUM_ROWS; r++)
        {
            for(int c = 0; c < RuzzleBoard.NUM_COLS; c++)
            {
                guiBoard[r][c].setText("");
                boardPanels[r][c].setBackground(Color.gray);
            }
        }
        solved = false;
        solution = new RuzzleSolution[0];
        lstSolution.setListData(solution);
        guiBoard[0][0].requestFocus();
    }
    
    private class GuiBoardListener implements DocumentListener {

      int row, col;

      public GuiBoardListener(int r, int c)
      {
         row = r;
         col = c;
      }

      
      public void insertUpdate(DocumentEvent e)
      {
         transferFocus();
      }

      public void removeUpdate(DocumentEvent e)
      {
         transferFocus();
      }

      public void changedUpdate(DocumentEvent e)
      {
         transferFocus();
      }

      public void transferFocus()
      {
          if(row == RuzzleBoard.NUM_ROWS-1 && col == RuzzleBoard.NUM_COLS - 1)
          {
              
          }
          else
          {
            guiBoard[row][col].transferFocus();
          }
      }
   }
}
