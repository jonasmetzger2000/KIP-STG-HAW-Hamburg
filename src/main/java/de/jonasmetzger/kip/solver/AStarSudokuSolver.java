package de.jonasmetzger.kip.solver;

import de.jonasmetzger.kip.sudoku.Board;

import java.util.*;

public abstract class AStarSudokuSolver {

    private final PriorityQueue<Board> boards = new PriorityQueue<>(Comparator.comparingInt(this::heuristic).reversed());

    protected AStarSudokuSolver(Board board) {
        boards.add(board);
    }

    public Board solve() {
        Board board = null;
        while (!boards.isEmpty() && !boards.peek().solved()) {
            board = boards.poll();
            final List<Board> nextBoards = nextMoves(board);
            boards.addAll(nextBoards);
        }
        return boards.poll();
    }

    protected List<Board> nextMoves(Board board) {
        final List<Board> nextPossibleBoards = new ArrayList<>();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                final List<Integer> possibleNums = board.numsForCell(x, y);
                for (Integer num : possibleNums) {
                    final Board newBoard = board.clone();
                    newBoard.set(num, x, y);
                    nextPossibleBoards.add(newBoard);
                }
            }
        }
        return nextPossibleBoards;
    }

    protected abstract int heuristic(Board board);

}
