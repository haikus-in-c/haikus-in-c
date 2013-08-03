/*	SudokuBoard class for SudokuSolver project
	by Ian Zapolsky 6/29/13 */

import java.util.Scanner;

public class SudokuBoard {
	private SudokuBox[][] board;

	public SudokuBoard() {
		board = new SudokuBox[9][9];
		buildBoardFromInput();
	}

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
		
