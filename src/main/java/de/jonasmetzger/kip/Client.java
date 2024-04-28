package de.jonasmetzger.kip;

import de.jonasmetzger.kip.solver.AStarSudokuSolver;
import de.jonasmetzger.kip.sudoku.Board;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Client {

    public static void main(String[] args) {
        String puzzle = load("sodoku2.txt");
        AStarSudokuSolver solver = new AStarSudokuSolver(Board.from(puzzle)) {
            @Override
            protected int heuristic(Board board) {
                if (board.solved()) return Integer.MAX_VALUE;
                else return board.countSetFields();
            }
        };

        Board solved = solver.solve();
        System.out.println(solved);
//        String puzzle = load("sodoku1.txt");
//
//        Board board = Board.from(puzzle);
//
//        System.out.println(puzzle);
//        System.out.println("###");
//        System.out.println(board);
//        System.out.println(board.toString().equals(puzzle));
    }

    static Board setBoard() {
        Board board = new Board();

        board.set(6, 0, 0);
        board.set(3, 1, 0);
        board.set(5, 2, 0);
        board.set(7, 3, 0);
        board.set(2, 4, 0);
        board.set(1, 5, 0);
        board.set(8, 6, 0);
        board.set(4, 7, 0);
        board.set(9, 8, 0);

        board.set(2, 0, 1);
        board.set(7, 1, 1);
        board.set(8, 2, 1);
        board.set(9, 3, 1);
        board.set(4, 4, 1);
        board.set(5, 5, 1);
        board.set(1, 6, 1);
        board.set(6, 7, 1);
        board.set(3, 8, 1);

        board.set(9, 0, 2);
        board.set(1, 1, 2);
        board.set(4, 2, 2);
        board.set(6, 3, 2);
        board.set(8, 4, 2);
        board.set(3, 5, 2);
        board.set(7, 6, 2);
        board.set(2, 7, 2);
        board.set(5, 8, 2);

        board.set(4, 0, 3);
        board.set(9, 1, 3);
        board.set(7, 2, 3);
        board.set(8, 3, 3);
        board.set(5, 4, 3);
        board.set(2, 5, 3);
        board.set(6, 6, 3);
        board.set(3, 7, 3);
        board.set(1, 8, 3);

        board.set(3, 0, 4);
        board.set(8, 1, 4);
        board.set(6, 2, 4);
        board.set(1, 3, 4);
        board.set(9, 4, 4);
        board.set(4, 5, 4);
        board.set(2, 6, 4);
        board.set(5, 7, 4);
        board.set(7, 8, 4);

        board.set(1, 0, 5);
        board.set(5, 1, 5);
        board.set(2, 2, 5);
        board.set(3, 3, 5);
        board.set(7, 4, 5);
        board.set(6, 5, 5);
        board.set(9, 6, 5);
        board.set(8, 7, 5);
        board.set(4, 8, 5);

        board.set(5, 0, 6);
        board.set(4, 1, 6);
        board.set(1, 2, 6);
        board.set(2, 3, 6);
        board.set(6, 4, 6);
        board.set(7, 5, 6);
        board.set(3, 6, 6);
        board.set(9, 7, 6);
        board.set(8, 8, 6);

        board.set(7, 0, 7);
        board.set(6, 1, 7);
        board.set(9, 2, 7);
        board.set(4, 3, 7);
        board.set(3, 4, 7);
        board.set(8, 5, 7);
        board.set(5, 6, 7);
        board.set(1, 7, 7);
        board.set(2, 8, 7);

        board.set(8, 0, 8);
        board.set(2, 1, 8);
        board.set(3, 2, 8);
        board.set(5, 3, 8);
        board.set(1, 4, 8);
        board.set(9, 5, 8);
        board.set(4, 6, 8);
        board.set(7, 7, 8);
        board.set(6, 8, 8);
        return board;
    }

    static String load(String str) {
        try {
            return Files.readString(Path.of(Objects.requireNonNull(Client.class.getResource("/" + str)).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

}
