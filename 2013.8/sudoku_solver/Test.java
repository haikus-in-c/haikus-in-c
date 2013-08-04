public class Test {

	public static void main(String[] args) {
		SudokuBoard puzzle = new SudokuBoard();
		SudokuSolver genius = new SudokuSolver();
		genius.solve(puzzle);
	}
}
