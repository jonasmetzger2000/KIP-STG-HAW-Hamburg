package de.jonasmetzger.kip2.sudoku;

import lombok.Getter;

@Getter
public class Sudoku {

    private final Cell[] cells;
    private final Column[] columns;
    private final Row[] rows;
    private final Block[] blocks;

    protected Sudoku() {
        this.cells = SudokuHelper.initCells();
        this.columns = SudokuHelper.initColumns(cells);
        this.rows = SudokuHelper.initRows(cells);
        this.blocks = SudokuHelper.initBlocks(columns);
    }

    /**
     * Erstellt ein neues Sodoku Feld, Felder die so gesetzt werden, können nicht geändert werden
     * @param sudokuString
     * @return
     */
    public static Sudoku from(String sudokuString) {
        final String[] cells = sudokuString.split(" {2}|\\n");
        final Sudoku sudoku = new Sudoku();
        for (int i = 0; i < cells.length; i++) {
            final String cellString = cells[i].strip();
            if (!cellString.equals("·")) {
                final int cell = Integer.parseInt(cellString);
                sudoku.cells[i].set(cell);
                sudoku.cells[i].setMutable(false);
            } else {
                sudoku.cells[i].setMutable(true);
                sudoku.cells[i].set(0);
            }
        }
        return sudoku;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Column col : columns) {
            for (Cell cell : col.getCells()) {
                if (cell.getValue() == 0) sb.append("·").append("  ");
                else sb.append(cell.getValue()).append("  ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
