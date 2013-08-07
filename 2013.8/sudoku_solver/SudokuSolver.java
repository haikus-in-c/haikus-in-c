/*	SudokuSolver class for sudoku_solver project
	by Ian Zapolsky (8/3/13) */

public class SudokuSolver {
	int passes;
	
	public SudokuSolver() {
		passes = 0;
	}

	public void solve(SudokuBoard board) {
		while (!board.isComplete()) {
			System.out.println("FILLING ALL\n");
			board.fillPossibleAll();
			System.out.println("EDITING ALL\n");
			board.editPossible();
			System.out.println("CHECKING ALL\n");
			if (board.checkToSetAll() == true)
				System.out.println("change made");
			else
				System.out.println("no changes made");
			System.out.println(board);
		}
		System.out.println("\nsolved!\n");
	}
}


