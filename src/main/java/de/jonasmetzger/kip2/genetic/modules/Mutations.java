package de.jonasmetzger.kip2.genetic.modules;

import de.jonasmetzger.kip2.genetic.EvolutionSudoku;
import de.jonasmetzger.kip2.random.RandomHelper;
import de.jonasmetzger.kip2.sudoku.Cell;

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
            List<Cell> toMutate = Arrays.asList(sudoku.getSudoku().getCells());
            Collections.shuffle(toMutate);
            for (int i = 0; i < n; i++) {
                final Cell cell = toMutate.get(i);
                if (cell.isMutable()) cell.set(RandomHelper.getSingleNumber());
            }
        }
    }

}
