/*	SudokuBoard class for sudoku_solver project
	by Ian Zapolsky (7/29/13) */

import java.util.Scanner;
import java.util.ArrayList;

public class SudokuBoard {
	private SudokuBox[][] board;
	private ArrayList<Integer> possibilities;

	public SudokuBoard() {
		board = new SudokuBox[9][9];
		possibilities = new ArrayList<Integer>();
		buildBoardFromInput();
	}

	/* 	methods to allow command line input of a sudoku board */
	private void buildBoardFromInput() {
		Scanner in = new Scanner(System.in);
		System.out.println("\twelcome to sudoku_helper. please enter your sudoku board one row at a time. enter 0s for all blank squares. do not put any whitespace or other characters between the 9 numbers representing a row.");
		for (int i = 0; i < 9; i++) {
			System.out.print("please input row "+(i+1)+": ");
			String input = in.nextLine();
			while (checkInput(input) == false) {
				System.out.print("i'm sorry, something went wrong with that row. please make sure you are entering an uninterrupted string of 9 integers to represent a row, where 0s represent blank squares on your sudoku board.\nplease input row "+(i+1)+": ");
				input = in.nextLine();
			}
			for (int j = 0; j < 9; j++) {
				board[i][j] = new SudokuBox(i, j);
				if (input.charAt(j) != '0')
					board[i][j].setValue(Integer.valueOf(input.substring(j, j+1)));
			}
		}
	}

	private boolean checkInput(String input) {
		if (input.length() != 9)
			return false;
		else {
			for (int i = 0; i < 9; i++) {
				if (input.charAt(i) < '0' || input.charAt(i) > '9')
					return false;
			}
		}
		return true;
	}

	/*	methods to determine and set all the possible values for each box on the board */ 
	public void fillPossibleAll() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				fillPossible(board[i][j]);
		}
	}

	private void fillPossible(SudokuBox current) {
		if (current.hasValue())
			/* do nothing */;
		else
			current.addPossibleValues(findPossibilities(current));
	}

	private ArrayList<Integer> findPossibilities(SudokuBox current) {
		initPossibilities();
		checkRow(current.getRow());
		checkColumn(current.getColumn());
		checkBox(current.getBox());
		return possibilities;
	}

	private void initPossibilities() {
		possibilities.clear();
		for (int i = 1; i < 10; i++)
			possibilities.add(i);
	}

	private void checkRow(int row) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i].hasValue())
				removePossibility(board[row][i].getValue());
		}
	}

	private void checkColumn(int column) {
		for (int i = 0; i < 9; i++) {
			if (board[i][column].hasValue())
				removePossibility(board[i][column].getValue());
		}
	}
	
	private void checkBox(int box) {
		for (int i = getBoxStartRow(box); i < getBoxEndRow(box); i++) {	
			for (int j = getBoxStartColumn(box); j < getBoxEndColumn(box); j++) {
				if (board[i][j].hasValue())
					removePossibility(board[i][j].getValue());
			}
		}
	}

	private void removePossibility(int value) {
		for (int i = 0; i < possibilities.size(); i++) {
			if (possibilities.get(i) == value) {
				possibilities.remove(i);
				break;
			}
		}
	}

	/*	methods to remove possible values on the board, based on proven sudoku solving methods */
	public void editPossible() {
		boxRowColumnInteraction();
	}

	private void boxRowColumnInteraction() {
		for (int box = 0; box < 9; box++) {
			for (int value = 1; value < 10; value++) {
				removeWithinRow(checkUniqueWithinRow(box, value), box, value);
				removeWithinColumn(checkUniqueWithinColumn(box, value), box, value);
			}
		}
	}

	private void removeWithinRow(int row, int box_to_avoid, int value) {
		if (row == 0)
			/* do nothing */;
		else {
			for (int i = 0; i < 9; i++) {
				if (i == getBoxStartColumn(box_to_avoid) || i == (getBoxStartColumn(box_to_avoid)+1) ||
					i == (getBoxStartColumn(box_to_avoid)+2))
					/* do nothing */;
				else
					board[row][i].remove(value);
			}
		}
	}

	private void removeWithinColumn(int column, int box_to_avoid, int value) {
		if (column == 0)
			/* do nothing */;
		else {
			for (int i = 0; i < 9; i++) {
				if (i == getBoxStartRow(box_to_avoid) || i == (getBoxStartRow(box_to_avoid)+1) ||
					i == (getBoxStartRow(box_to_avoid)+2))
					/* do nothing */;
				else 
					board[i][column].remove(value);
			}
		}
	}

	private int checkUniqueWithinRow(int box, int value) {
		boolean first_row, second_row, third_row;
		first_row = second_row = third_row = false;
		for (int i = getBoxStartRow(box); i < getBoxEndRow(box); i++) {
			for (int j = getBoxStartColumn(box); j < getBoxEndColumn(box); j++) {
				if (board[i][j].checkPossibleValues(value) == true) {
					if (i == getBoxStartRow(box))
						first_row = true;
					else if (i == (getBoxStartRow(box)+1))
						second_row = true;
					else
						third_row = true;
				}
			}
		}
		if (first_row && !second_row && !third_row)
			return getBoxStartRow(box);
		else if (!first_row && second_row && !third_row)
			return (getBoxStartRow(box)+1);
		else if (!first_row && !second_row && third_row)
			return (getBoxStartRow(box)+2);
		else
			return 0;
	}

	private int checkUniqueWithinColumn(int box, int value) {
		boolean first_column, second_column, third_column;
		first_column = second_column = third_column = false;
		for (int i = getBoxStartRow(box); i < getBoxEndRow(box); i++) {
			for (int j = getBoxStartColumn(box); j < getBoxEndColumn(box); j++) {
				if (board[i][j].checkPossibleValues(value) == true) {
					if (j == getBoxStartColumn(box))
						first_column = true;
					else if (j == (getBoxStartColumn(box)+1))
						second_column = true;
					else
						third_column = true;
				}
			}
		}
		if (first_column && !second_column && !third_column)
			return getBoxStartColumn(box);
		else if (!first_column && second_column && !third_column)
			return (getBoxStartColumn(box)+1);
		else if (!first_column && !second_column && third_column)
			return (getBoxStartColumn(box)+2);
		else
			return 0;
	}

	/*	methods to set the value of individual boxes on the board, based on the information we have about their possible values */ 
	public boolean checkToSetAll() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (checkToSet(board[i][j]) == true)
					return true;
			}
		}
		return false;
	}

	private boolean checkToSet(SudokuBox current) {
		if (current.hasValue())
			return false;
		else if (current.getSizePossibleValues() == 1) {
			current.setValue(current.getPossibleValues().get(0));
			return true;
		}
		else {
			for (int i : current.getPossibleValues()) {
				if (checkUniqueRow(i, current.getRow()) == true) {
					current.setValue(i);
					return true;
				}
				if (checkUniqueColumn(i, current.getColumn()) == true) {
					current.setValue(i);
					return true;
				}
				if (checkUniqueBox(i, current.getBox()) == true) {
					current.setValue(i);
					return true;
				}
			}
			return false;
		}
	}

	private boolean checkUniqueRow(int value, int row) {
		int occurrences = 0;
		for (int i = 0; i < 9; i++) {
			if (board[row][i].checkPossibleValues(value) == true) 
				occurrences++;
		}
		if (occurrences == 1)
			return true;
		else
			return false;
	}
	
	private boolean checkUniqueColumn(int value, int column) {
		int occurrences = 0;
		for (int i = 0; i < 9; i++) {
			if (board[i][column].checkPossibleValues(value) == true)
				occurrences++;
		}
		if (occurrences == 1)
			return true;
		else
			return false;
	}

	private boolean checkUniqueBox(int value, int box) {
		int occurrences = 0;
		for (int i = getBoxStartRow(box); i < getBoxEndRow(box); i++) {
			for (int j = getBoxStartColumn(box); j < getBoxEndColumn(box); j++) {
				if (board[i][j].checkPossibleValues(value) == true)
					occurrences++;
			}
		}
		if (occurrences == 1)
			return true;
		else
			return false;
	}

	public boolean isComplete() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!board[i][j].hasValue())
					return false;
			}
		}
		return true;
	}

	private int getBoxStartRow(int box) {
		return ((box/3)*3);
	}
	
	private int getBoxEndRow(int box) {
		return (getBoxStartRow(box) + 3);
	}

	private int getBoxStartColumn(int box) {
		return ((box%3)*3);
	}

	private int getBoxEndColumn(int box) {
		return (getBoxStartColumn(box) + 3);
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				result += String.valueOf(board[i][j].getValue());
				if ((i == 2 && j == 8) || (i == 5 && j == 8))
					result += "\n";
			}
			result += "\n";
		}
		return result;
	}
}
		
