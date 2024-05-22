package de.jonasmetzger.kip2.genetic.modules;

import de.jonasmetzger.kip2.genetic.EvolutionSudoku;
import de.jonasmetzger.kip2.random.RandomHelper;
import de.jonasmetzger.kip2.sudoku.Cell;
import de.jonasmetzger.kip2.sudoku.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mutations {

    /**
     * Mutiert n Felder in jeden Sudoku
     * @param sudokus
     * @param n anzahl an zu mutierenden Felder im Sudoku
     * @return mutierte Sudokus
     */
    public static void mutateFields(List<EvolutionSudoku> sudokus, int n) {
        for (EvolutionSudoku sudoku : sudokus) {
            Column columnToMutate = RandomHelper.getRandomColumn(sudoku.getSudoku());
            final List<Cell> cellsToMutate = columnToMutate.getCells().stream().filter(Cell::isMutable).toList();
            for (int i = 0; i < n; i++) {
                final Cell first = RandomHelper.getOneFromList(cellsToMutate);
                final Cell second = RandomHelper.getOneFromList(cellsToMutate);
                int tmp = first.getValue();
                first.set(second.getValue());
                second.set(tmp);
            }
        }
    }

}
