package guidemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//James Moseley CS 211 Winter 2020
//ReadNums Class is simply reading the file with a scanner

public class ReadNums {

	// the getData method takes the parameters: a file and an array and processes
	// the data from the original file

	public void getData(String fileName, int[][] board) throws FileNotFoundException {

		int i = 0;

		File file = new File(fileName);
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String text = scan.nextLine();
			for (int j = 0; j < text.length(); j++) {
				if (text.charAt(j) == ('.')) {
					board[i][j] = 0;

				} else {
					board[i][j] = text.charAt(j) - '0';

				}
			}
			i++;
		}

	}

}
