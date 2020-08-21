package Sudoku;

import java.io.FileNotFoundException;

public class client {

	public static void main(String[] args) throws FileNotFoundException {

		int[][] board = new int[9][9];
		ReadNums nums = new ReadNums();
		nums.getData("test.txt", board);
		SudokuPart part = new SudokuPart(board);

	}

}
