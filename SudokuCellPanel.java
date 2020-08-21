package Sudoku;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

//SudokuCellPanel is the JPanel object we will be using

public class SudokuCellPanel extends JPanel {

	private JLabel txt = new JLabel();

	// sudokuCellPanel just sets up the panels that we will be using in the frame
	public SudokuCellPanel() {

		setBorder(BorderFactory.createLineBorder(Color.BLUE));

		setPreferredSize(new Dimension(150, 150));
	}

	// setData just adds the data into the gui layout
	public void setData(int t) {
		add(txt);
		txt.setText(t + "");
	}

}
