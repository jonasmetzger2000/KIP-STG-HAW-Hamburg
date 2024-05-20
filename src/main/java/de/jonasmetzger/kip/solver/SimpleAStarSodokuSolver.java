package de.jonasmetzger.kip.solver;

import de.jonasmetzger.kip.sudoku.BoardArray;

import java.util.List;

public class SimpleAStarSodokuSolver extends AStarSudokuSolver{
    public SimpleAStarSodokuSolver(BoardArray board) {
        super(board);
    }

    @Override
    protected int heuristic(BoardArray board, int x, int y, List<Integer> possibleNums) {
        if (board.solved()) return -1;
        return (possibleNums.size()-1)*board.countSetFields();
    }
}
