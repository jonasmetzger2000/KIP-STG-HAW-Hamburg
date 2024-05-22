package de.jonasmetzger.kip2.genetic.modules;

import de.jonasmetzger.kip2.sudoku.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fitness {

    /**
     * Berechnet die Fitness anhand der Konflikte im Block/Spalte oder in der Zeile ()
     * @param board
     * @return beste Fitness = 0, desto niedriger desto besser
     */
    public static int conflictsWithinSudoku(Sudoku board) {
        int conflicts = 0;
        for (Column column : board.getColumns()) {
            conflicts += getConflicts(column.getCells());
        }
        for (Row row : board.getRows()) {
            conflicts += getConflicts(row.getCells());
        }
        for (Block block : board.getBlocks()) {
            conflicts += getConflicts(block.getCells());
        }
        return conflicts;
    }

    private static int getConflicts(List<Cell> column) {
        int conflicts = 0;
        Set<Integer> duplicates = new HashSet<>();
        for (Cell cell : column) {
            if (!duplicates.add(cell.getValue())) conflicts++;
        }
        return conflicts;
    }
}
