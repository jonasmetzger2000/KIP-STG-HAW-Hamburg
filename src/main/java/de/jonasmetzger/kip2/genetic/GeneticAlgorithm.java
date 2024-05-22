package de.jonasmetzger.kip2.genetic;

import de.jonasmetzger.kip2.Utils;
import de.jonasmetzger.kip2.genetic.modules.Fitness;
import de.jonasmetzger.kip2.genetic.modules.Mutations;
import de.jonasmetzger.kip2.genetic.modules.Recombination;
import de.jonasmetzger.kip2.genetic.modules.Selection;
import de.jonasmetzger.kip2.sudoku.Sudoku;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {

    private static final int POPULATION_SIZE = 5000;

    private final List<Sudoku> victoriousPopulation = new ArrayList<>();
    private final Sudoku initialBoard = Sudoku.from(Utils.load("sodoku4.txt"));

    private List<EvolutionSudoku> population = new ArrayList<>(POPULATION_SIZE);
    private int round = 1;

    public void start() {
        // Start: Population erzeugen
        for (int i = 0; i < POPULATION_SIZE; i++) {
            final Sudoku board = initialBoard.clone();
            SudokuGenetics.fillColumnRandom(board);
            population.add(new EvolutionSudoku(round, Integer.MAX_VALUE, board));
        }

        while (victoriousPopulation.isEmpty()) {
            // Berechnung der Fitness
            for (EvolutionSudoku evolutionSudoku : population) {
                evolutionSudoku.setRound(round);
                final int fitness = Fitness.conflictsWithinSudoku(evolutionSudoku.getSudoku());
                if (fitness == 0) victoriousPopulation.add(evolutionSudoku.getSudoku());
                evolutionSudoku.setFitness(fitness);
            }

            round++;
            // Printing Information
            if (round % 100000 == 0) {
                population.sort(EvolutionSudoku::compareTo);
                System.out.println(population.getFirst());
            }

            // Selektion
            population = Selection.tournamentSelection(population);

            // Recombination
            population = Recombination.columnFromParentOneOrTwo(initialBoard, population, 0.8d, 0.2d);

            // Mutation
            Mutations.mutateFields(population, 5);
        }
    }

}
