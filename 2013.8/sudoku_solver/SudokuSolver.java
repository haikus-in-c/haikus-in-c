/*  SudokuSolver class for sudoku_solver project
    by Ian Zapolsky (8/3/13) */

public class SudokuSolver {
    int passes;
    int no_change;
    
    public SudokuSolver() {
        passes = 0;
        no_change = 0;
    }

    public void solve(SudokuBoard board) {
        while (!board.isComplete()) {

            // System.out.println("FILLING ALL\n");
            board.fillPossibleAll();
            // System.out.println("EDITING ALL\n");
            board.editPossible();
            // System.out.println("CHECKING ALL\n");
    
            if (board.checkToSetAll() != true)
                no_change++;

            passes++;

            if (no_change > 100) {
                System.out.println("couldn't solve!\n\n"+board);
                return;
            }
        }
        System.out.println("\nsolved!\n\n"+board+"\n\n"+"passes: "+passes);
    }
}


