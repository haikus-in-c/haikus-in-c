/*	SudokuBoard class for sudoku_solver project
	by Ian Zapolsky (7/29/13) */

import java.util.Scanner;

public class SudokuBoard {
	private SudokuBox[][] board;

	public SudokuBoard() {
		board = new SudokuBox[9][9];
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
			current.addPossible(findPossibilities(current));
	}

	private ArrayList<Integer> findPossibilities(SudokuBox current) {
		ArrayList<Integer> possibilities = initPossibilities();
		possibilities = checkRow(possibilities, current.getRow());
		possibilities = checkColumn(possibilities, current.getColumn());
		possibilities = checkBox(possibilities, current.getBox());
		return possibilities;
	}

	private ArrayList<Integer> initPossibilities() {
		ArrayList<Integer> possibilities = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++)
			possibilities.add(i);
		return possibilities;
	}

	private ArrayList<Integer> checkRow(ArrayList<Integer> possibilities, int row) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i].hasValue())
				possibilities.remove(board[row][i].getValue());
		}
		return possibilities;
	}

	private ArrayList<Integer> checkColumn(ArrayList<Integer> possibilities, int column) {
		for (int i = 0; i < 9; i++) {
			if (board[i][column].hasValue())
				possibilities.remove(board[i][column].getValue());
		}
		return possibilities;
	}
	
	private ArrayList<Integer> checkBox(ArrayList<Integer> possibilities, int box) {
		for (int i = ((box/3)*3); i < (i+3); i++) {		/* box tricky calculation */
			for (int j = ((box%3)*3); j < (j+3); j++) {
				if (board[i][j].hasValue())
					possibilities.remove(board[i][j].getValue());
			}
		}
		return possibilities;
	}

	/* 	methods to set the value of individual boxes on the board, based on the information we have about their possible values */ 
	public void checkToSetAll() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				checkToSet(board[i][j]);
		}
	}

	private void checkToSet(SudokuBox current) {
		if (current.hasValue())
			/* do nothing */;
		else if (current.getSizePossible() == 1)
			current.setValue(current.getPossible.get(0));
		else {
			for (int i : current.getPossible()) {
				if (checkUniqueRow(i, current.getRow()) == true) {
					current.setValue(i);
					break;
				}
				if (checkUniqueColumn(i, current.getColumn()) == true) {
					current.setValue(i);
					break;
				}
				if (checkUniqueBox(i, current.getBox()) == true) {
					current.setValue(i);
					break;
				}
			}
		}
	}

	private boolean checkUniqueRow(int value, int row) {
		int occurrences = 0;
		for (int i = 0; i < 9; i++) {
			if (board[row][i].checkPossible(value) == true) 
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
			if (board[i][column].checkPossible(value) == true)
				occurrences++;
		}
		if (occurrences == 1)
			return true;
		else
			return false;
	}

	private boolean checkUniqueBox(int value, int box) {
		int occurrences = 0;
		for (int i = ((box/3)*3); i < (i+3); i++) {		/*	box tricky calculation again */
			for (int j = ((box%3)*3); j < (j+3); j++) {	
				if (board[i][j].checkPossible(value) == true)
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

	public String toString() {
		String result = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				result += String.valueOf(board[i][j].getValue());
			result += "\n";
		}
		return result;
	}
}
		
