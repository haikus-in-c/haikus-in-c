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

	/* 	methods to determine all the possible values for each box on the board */ 
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
		System.out.println(current);
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
				remove(board[row][i].getValue());
		}
	}

	private void checkColumn(int column) {
		for (int i = 0; i < 9; i++) {
			if (board[i][column].hasValue())
				remove(board[i][column].getValue());
		}
	}
	
	private void checkBox(int box) {
		int row_start = ((box/3)*3);
		int row_end = row_start+3;

		int column_start = ((box%3)*3);
		int column_end = column_start+3;

		for (int i = row_start; i < row_end; i++) {	
			for (int j = column_start; j < column_end; j++) {
				if (board[i][j].hasValue())
					remove(board[i][j].getValue());
			}
		}
	}

	private void remove(int value) {
		for (int i = 0; i < possibilities.size(); i++) {
			if (possibilities.get(i) == value) {
				possibilities.remove(i);
				break;
			}
		}
	}

	/* 	methods to set the value of individual boxes on the board, based on the information we have about their possible values */ 
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
		System.out.println("occurrences for "+value+" in row "+row+": "+occurrences);
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
		System.out.println("occurrences for "+value+" in column "+column+": "+occurrences);
		if (occurrences == 1)
			return true;
		else
			return false;
	}

	private boolean checkUniqueBox(int value, int box) {
		int occurrences = 0;
		for (int i = ((box/3)*3); i < (((box+1)/3)*3); i++) {		/* box tricky calculation again */
			for (int j = ((box%3)*3); j < (((box+1)%3)*3); j++) {
				if (board[i][j].checkPossibleValues(value) == true)
					occurrences++;
			}
		}
		System.out.println("occurrences for "+value+" in box "+box+": "+occurrences);
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
		
