package de.jonasmetzger.kip1.genetic;

import de.jonasmetzger.kip1.sudoku.BoardArrayV2;

public class GeneticSodokuSolver {


    private final BoardArrayV2 currentBoard;

    public GeneticSodokuSolver(BoardArrayV2 currentBoard) {
        this.currentBoard = currentBoard;
    }

    public static Integer fitness(BoardArrayV2 board) {
        return 0;
    }
}
