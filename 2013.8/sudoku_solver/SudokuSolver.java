/*	SudokuSolver class for sudoku_solver project
	by Ian Zapolsky (8/3/13) */

public class SudokuSolver {
	int passes;
	
	public SudokuSolver() {
		passes = 0;
	}

	public void solve(SudokuBoard board) {
		while (!board.isComplete()) {
			board.fillPossibleAll();
			board.checkToSetAll();
			System.out.println(board);
		}
		System.out.println("\nsolved!\n");
	}
}


