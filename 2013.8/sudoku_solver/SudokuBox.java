/*	SudokuBox class for SudokuSolver project
	by Ian Zapolsky (7/29/13) */

public class SudokuBox {

	private int value, row, column, square;
	private ArrayList<int> possibleValues;

	public SudokuBox(int init_row, int init_column, int init_square) {
		value = 0;
		row = init_row;
		column = init_column;
		square = init_square;
		possibleValues = new ArrayList<int>();
	}

	public void addPossible(int value) {
		possibleValues.add(value);
	}
		
	public void removePossible(int value) {
		possibleValues.remove(value);
	}

	public int getPossible() { 
		return possibleValues.size(); 
	}
		
	public int getRow() { 
		return row; 
	}

	public int getColumn() { 
		return column; 
	}

	public int getSquare() { 
		return square; 
	}

}
			
