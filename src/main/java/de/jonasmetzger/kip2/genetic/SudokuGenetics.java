package de.jonasmetzger.kip2.genetic;

import de.jonasmetzger.kip2.sudoku.Cell;
import de.jonasmetzger.kip2.sudoku.Column;
import de.jonasmetzger.kip2.sudoku.Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuGenetics {

    private static final List<Integer> VALUES = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * Befüllt das Sudoku Zeile für Zeile, Duplikate können dabei nur Spalten ODER Blockweise entstehen
     * @param initialSudoku - das Sudoku welches mit zufallswerten befüllt werden soll
     */
    public static void fillColumnRandom(Sudoku initialSudoku) {
        for (Column column : initialSudoku.getColumns()) {
            final List<Integer> numbers = column.getCells().stream().filter(Cell::isSet).map(Cell::getValue).toList();
            final List<Integer> numsToFill = new ArrayList<>(VALUES);
            numsToFill.removeAll(numbers);
            Collections.shuffle(numsToFill);
            int i = 0;
            for (Cell cell : column.getCells()) {
                if (!cell.isSet()) {
                    cell.set(numsToFill.get(i++));
                }
            }
        }
    }

}
