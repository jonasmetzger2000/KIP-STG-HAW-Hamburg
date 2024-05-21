package de.jonasmetzger.kip2.sudoku;

import java.util.ArrayList;
import java.util.List;

class SudokuHelper {

    protected static Cell[] initCells() {
        Cell[] cells = new Cell[81];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(true, 0);
        }
        return cells;
    }

    protected static Column[] initColumns(Cell[] cells) {
        Column[] columns = new Column[9];
        for (int i = 0; i < columns.length; i++) {
            List<Cell> cellsForCol = new ArrayList<>();
            for (int j = i*9; j < i*9+9; j++) {
                cellsForCol.add(cells[j]);
            }
            final Column column = new Column(cellsForCol);
            columns[i] = column;
            for (Cell cell : cellsForCol) {
                cell.setColumn(column);
            }
        }
        return columns;
    }

    protected static Row[] initRows(Cell[] cells) {
        Row[] rows = new Row[9];
        for (int i = 0; i < rows.length; i++) {
            List<Cell> cellsForCol = new ArrayList<>();
            for (int j = i; j < cells.length; j+=9) {
                cellsForCol.add(cells[j]);
            }
            final Row row = new Row(cellsForCol);
            rows[i] = row;
            for (Cell cell : cellsForCol) {
                cell.setRow(row);
            }
        }
        return rows;
    }

    protected static Block[] initBlocks(Column[] grid) {
        Block[] blocks = new Block[9];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                final ArrayList<Cell> cells = new ArrayList<>();
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        cells.add(grid[x * 3 + row].getCells().get(y * 3 + col));
                    }
                }
                final Block block = new Block(cells);
                blocks[x * 3 + y] = block;
                for (Cell cell : cells) {
                    cell.setBlock(block);
                }
            }
        }
        return blocks;
    }
}
