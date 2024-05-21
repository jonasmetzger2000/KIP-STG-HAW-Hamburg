package de.jonasmetzger.kip2;

import de.jonasmetzger.kip2.genetic.SudokuGenetics;
import de.jonasmetzger.kip2.sudoku.Sudoku;

public class Client {

    public static void main(String[] args) {
        final Sudoku sudoku = Sudoku.from(Utils.load("sodoku2.txt"));
        System.out.println(sudoku);
        SudokuGenetics.fillColumnRandom(sudoku);
        System.out.println(sudoku);
    }
}
