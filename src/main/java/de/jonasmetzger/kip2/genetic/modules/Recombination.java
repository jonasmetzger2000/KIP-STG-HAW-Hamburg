package de.jonasmetzger.kip2.genetic.modules;

import de.jonasmetzger.kip2.genetic.EvolutionSudoku;
import de.jonasmetzger.kip2.random.RandomHelper;
import de.jonasmetzger.kip2.sudoku.Cell;
import de.jonasmetzger.kip2.sudoku.Column;
import de.jonasmetzger.kip2.sudoku.Sudoku;

import java.util.ArrayList;
import java.util.List;

public class Recombination {

    /**
     * One Point Crossover mittels einzelner Zeilen, es wird eine Zeile vom Parent oder Parent 2 genommen.
     * @param evolutionSudokus die zu rekombinierende Sudoku Felder
     */
    public static List<EvolutionSudoku> columnFromParentOneOrTwo(Sudoku initialBoard,
                                                                 List<EvolutionSudoku> evolutionSudokus,
                                                                 double shareChildren,
                                                                 double shareParents,
                                                                 int populationSize) {
        // adding all the "surviving" parents
        final List<EvolutionSudoku> nRandomSudokuBoards = RandomHelper.getNRandomSudokuBoards((int) (evolutionSudokus.size() * shareParents), evolutionSudokus);
        final List<EvolutionSudoku> result = new ArrayList<>(nRandomSudokuBoards);

        // create new offsprings
        for (int i = 0; i < populationSize * shareChildren * 2; i++) {
            final Sudoku children = initialBoard.clone();

            final List<Sudoku> parents = List.of(
                    RandomHelper.getRandomEvolutionSudokuReserve(evolutionSudokus).getSudoku(),
                    RandomHelper.getRandomEvolutionSudokuReserve(evolutionSudokus).getSudoku(),
                    RandomHelper.getRandomEvolutionSudokuReserve(evolutionSudokus).getSudoku()
            );

            for (int j = 0; j < 9; j++) {
                final Sudoku chosenParent = RandomHelper.getFromList(parents);
                final Column parentColumn = chosenParent.getColumns()[j];
                for (int k = 0; k < parentColumn.getCells().size(); k++) {
                    final Cell cell1 = chosenParent.getCell(j, k);
                    final Cell childrenCell = children.getCell(j, k);
                    if (childrenCell.isMutable()) {
                        childrenCell.set(cell1.getValue());
                    }
                }
            }
            result.add(new EvolutionSudoku(evolutionSudokus.getFirst().getRound(), Double.NaN, children));
        }
        return result;
    }



    public static List<EvolutionSudoku> crossover(List<EvolutionSudoku> population,
                                                  List<EvolutionSudoku> selectedInviduals,
                                                  int populationSize) {
        final List<EvolutionSudoku> newPopulation = new ArrayList<>();
        for (EvolutionSudoku evolutionSudoku : population) {
            final Sudoku child = evolutionSudoku.getSudoku();
            final Sudoku parent1 = RandomHelper.getOneFromList(selectedInviduals).getSudoku();
            final Sudoku parent2 = RandomHelper.getOneFromList(selectedInviduals).getSudoku();

            for (int i = 0; i < child.getCells().length; i++) {
                Cell cell = child.getCells()[i];
                if (cell.isMutable()) {
                    if (RandomHelper.trueOrFalse()) cell.set(parent1.getCell(i).getValue());
                    else cell.set(parent2.getCell(i).getValue());
                }
            }
            newPopulation.add(new EvolutionSudoku(population.getFirst().getRound(), Double.NaN, child));
        }
        return newPopulation;
    }


}
