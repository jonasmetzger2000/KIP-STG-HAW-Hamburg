package de.jonasmetzger.kip1.solver;

import de.jonasmetzger.kip1.sudoku.BoardArray;

import java.util.*;

public abstract class AStarSudokuSolver {

    private final PriorityQueue<Map.Entry<Integer, BoardArray>> boards = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));

    protected AStarSudokuSolver(BoardArray board) {
        boards.add(Map.entry(1, board));
    }

    public BoardArray solve() {
        BoardArray board;
        while (!boards.isEmpty() && !boards.peek().getValue().solved()) {
            board = Objects.requireNonNull(boards.poll()).getValue();
            final Map<Integer, BoardArray> nextBoards = nextMoves(board);
            boards.addAll(nextBoards.entrySet());
        }
        return Objects.requireNonNull(boards.poll()).getValue();
    }

    protected Map<Integer, BoardArray> nextMoves(BoardArray board) {
        final Map<Integer, BoardArray> nextPossibleBoards = new TreeMap<>(Comparator.comparingInt(value -> value));
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                final List<Integer> possibleNums = board.numsForCell(x, y);
                if (possibleNums.size() == 1) {
                    final BoardArray newBoard = board.clone();
                    newBoard.set(possibleNums.getFirst(), x, y);
                    nextPossibleBoards.put(heuristic(board, x, y, possibleNums), newBoard);
                }
            }
        }
        return nextPossibleBoards;
    }

    protected abstract int heuristic(BoardArray board, int x, int y, List<Integer> possibleNums);

}
