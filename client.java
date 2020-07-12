package guidemo;

import java.io.FileNotFoundException;

//James Moseley CS 211 Winter 2020
//this is the client class


public class client {

	public static void main(String[] args) throws FileNotFoundException {

		int[][] board = new int[9][9];
		ReadNums nums = new ReadNums();
		nums.getData("test.txt", board);
		SudokuPart part = new SudokuPart(board);

	}

}
