/*	SudokuBoard class for SudokuSolver project
	by Ian Zapolsky 6/29/13 */

public class SudokuBoard {

	private SudokuBox[][] board;

	public SudokuBoard() {
		initBoard();
	}

	private void initBoard() {
		board = new SudokuBox[9][9];
