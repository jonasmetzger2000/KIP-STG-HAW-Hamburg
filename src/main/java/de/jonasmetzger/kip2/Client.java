package de.jonasmetzger.kip2;

import de.jonasmetzger.kip2.genetic.GeneticAlgorithm;
import de.jonasmetzger.kip2.genetic.SudokuGenetics;
import de.jonasmetzger.kip2.sudoku.Sudoku;

public class Client {

    public static void main(String[] args) {
        new GeneticAlgorithm().start();
    }
}
