package Sudoku;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

//SudokuPart is main part of the program that will be setting up the GUI, 
//solving the puzzle, and implementing the button action listener

public class SudokuPart {

	SudokuPart[][] board = new SudokuPart[3][3];

	// isSafe Method checks rows and columns for repeating numbers
	public static boolean isSafe(int[][] board, int row, int col, int num) {
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == num) {
				return false;
			}
		}
		for (int j = 0; j < board.length; j++) {
			if (board[j][col] == num) {
				return false;
			}
		}
		int sqrt = (int) Math.sqrt(board.length);
		int rowStart = row - row % sqrt;
		int colStart = col - col % sqrt;

		for (int r = rowStart; r < rowStart + sqrt; r++) {
			for (int c = colStart; c < colStart + sqrt; c++) {
				if (board[r][c] == num) {
					return false;
				}
			}
		}
		return true;

	}

	// solve method solves the sudoku puzzle by using the isSafe method inside it
	public static boolean solve(int[][] board, int n) {
		int row = -1;
		int col = -1;
		boolean isEmpty = true;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					row = i;
					col = j;

					isEmpty = false;
					break;

				}
			}
			if (!isEmpty) {
				break;
			}
		}

		if (isEmpty) {
			return true;
		}

		for (int i = 1; i <= n; i++) {
			if (isSafe(board, row, col, i)) {
				board[row][col] = i;
				if (solve(board, n)) {
					return true;
				} else {
					board[row][col] = 0;
				}
			}
		}
		return false;

	}

	// SudokuPart sets up the board, it creates a 3x3 panel and each panel has its
	// own 3x3 panels
	// it also creates a button to implement the solve method and finally it has an
	// action listener to solve the puzzle
	public SudokuPart(int[][] board) {

		int[][] newBoard = new int[9][9];
		divideData(board, newBoard);

		JFrame frm = new JFrame("Sudoku Solver");
		JButton but = new JButton("Solve");

		List<SudokuCellPanel> pn = new ArrayList(); // original 3x3 arraylist panels
		for (int i = 0; i < 9; i++) {
			SudokuCellPanel s = new SudokuCellPanel();
			s.setLayout(new GridLayout(3, 3));
			insertPanels(s, newBoard[i]);
			pn.add(s);
		}

		frm.setLayout(new GridLayout(4, 3));

		for (SudokuCellPanel p : pn) { // adding panels to the frame
			frm.add(p);
		}

		but.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked");
				if (solve(board, 9)) {
					int[][] solvedBoard = new int[9][9];
					divideData(board, solvedBoard);

					for (int i = 0; i < 9; i++) {

						Component[] components = pn.get(i).getComponents();
						pn.get(i).removeAll();
						for (int j = 0; j < 9; j++) {
							SudokuCellPanel p = (SudokuCellPanel) components[j];

							p.setData(solvedBoard[i][j]);
							pn.get(i).add(p);
						}
					}
					frm.repaint();

				} else {
					System.out.println("no solutions");
				}
			}
		}

		);

		frm.add(but);

		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
		frm.pack();
		frm.setPreferredSize(new Dimension(800, 800));

	}

	// insertPanels creates additional 3x3 sudokucellpanels that are inserted into
	// the original 3x3 panels
	public void insertPanels(SudokuCellPanel p, int[] values) {

		for (int i = 0; i < 9; i++) {
			SudokuCellPanel s = new SudokuCellPanel();
			s.setData(values[i]);

			p.add(s);

		}

	}

	// because the gui is set up in 3x3 panels, a method is needed to organize the
	// array to better print to the gui
	// divideData separates the array into a 3x3 array so it can mimic the way the
	// gui is set up
	public void divideData(int[][] board, int[][] newBoard) {

		int startCol = 0;
		int startRow = 0;
		int rowNum = 0;
		for (startRow = 0; startRow < 9; startRow += 3) {
			for (startCol = 0; startCol < 9; startCol += 3) {
				int[] nextSet = new int[9];
				int index = 0;
				for (int i = startRow; i < startRow + 3; i++) {
					for (int j = startCol; j < startCol + 3; j++) {
						nextSet[index] = board[i][j];
						index++;
					}
				}
				newBoard[rowNum] = nextSet;
				rowNum++;
			}
		}
	}

}
