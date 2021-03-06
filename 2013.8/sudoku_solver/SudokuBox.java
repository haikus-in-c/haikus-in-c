/*  SudokuBox class for sudoku_solver project
    by Ian Zapolsky (7/29/13) */

import java.util.ArrayList;

public class SudokuBox {
    private int value, row, column, box;
    private ArrayList<Integer> possibleValues;
    
    public SudokuBox(int init_row, int init_column) {
        value = 0;
        row = init_row;
        column = init_column;
        possibleValues = new ArrayList<Integer>();
        setBox();
    }

    private void setBox() {
        if (row < 3) {
            if (column < 3)
                box = 0;
            else if (column < 6)
                box = 1;
            else
                box = 2;
        }
        else if (row < 6) {
            if (column < 3)
                box = 3;
            else if (column < 6)
                box = 4;
            else
                box = 5;
        }
        else {
            if (column < 3)
                box = 6;
            else if (column < 6)
                box = 7;
            else
                box = 8;
        }
    }

    public boolean hasValue() {
        if (value == 0)
            return false;
        else
            return true;
    }

    public void addPossibleValues(ArrayList<Integer> possibilities) {
        possibleValues.clear();
        for (int i : possibilities)
            possibleValues.add(i);
    }

    public void remove(int possibility) {
        for (int i = 0; i < possibleValues.size(); i++) {
            if (possibleValues.get(i) == value) {
                possibleValues.remove(i);
                break;
            }
        }
    }
    
    public boolean equals(SudokuBox other) {
        if (other.getSizePossibleValues() != this.getSizePossibleValues())
            return false;
        else {
            for (int i = 0; i < this.getSizePossibleValues(); i++) {
                if (this.getPossibleValueByIndex(i) != other.getPossibleValueByIndex(i))
                    return false;
            }
            return true;
        }
    }

    public boolean checkPossibleValues(int possibility) {
        for (int i : possibleValues) {
            if (i == possibility)
                return true;
        }
        return false;
    }

    public ArrayList<Integer> getPossibleValues() {
        return possibleValues;
    }

    public int getPossibleValueByIndex(int index) {
        return possibleValues.get(index);
    }

    public int getSizePossibleValues() {
        return possibleValues.size();
    }

    public int getValue() {
        return value;
    }
    
    public void setValue(int init_value) {
        value = init_value;
        possibleValues.clear();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getBox() {
        return box;
    }

    public String toString() {
        String result = ""+row+":"+column+"\n";
        for (int i : possibleValues)
            result += ""+i+", ";
        return result;
    }
}
