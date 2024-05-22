package de.jonasmetzger.kip1.sudoku;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static de.jonasmetzger.kip1.Utils.load;

public class BoardArrayV2 {

    Cell[] grid = new Cell[81];
    int errors = 0;

    public BoardArrayV2(String string) {
        String[] cells = string.split(" {2}|\\n");
        for (int i = 0; i < grid.length; i++) {
            final String cellStringValue = cells[i];
            if (cellStringValue.matches("\\d+")) {
                set(getX(i), getY(i), Integer.parseInt(cellStringValue), true);
            } else {
                set(getX(i), getY(i), 0, false);
            }
        }
    }

    public void set(int x, int y, int value) {
        set(x, y, value, false);
    }

    private void set(int x, int y, int value, boolean intial) {
        if (!intial && value != 0) {
            // block checking
            for (int i = getBlock(x, y)*9; i < grid.length; i+=9) {
                if (grid[i].value == value) {
                    errors++;
                } else if (grid[i+1].value == value) {
                    errors++;
                } else if (grid[i+2].value == value) {
                    errors++;
                }
            }
            // column and row checking
            for (int i = 0; i < 9; i++) {
                if (get(i, y).value == value) {
                    if (x != i) errors++;
                }
                if (get(x, i).value == value) {
                    if (y != i) errors++;
                }
            }
        }
        final int i = convert2Dto1D(x, y);
        grid[i] = new Cell(!intial, value);
    }

    public static void main(String[] args) {
        BoardArrayV2 board = new BoardArrayV2(load("old/sodoku2.txt"));

        System.out.println(board.errors);

        board.set(2, 0, 1);


        System.out.println(board.errors);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            if (i % 9 == 0) builder.append("\n");
            final int value = Optional.ofNullable(grid[i]).map(Cell::getValue).orElse(0);
            if (value != 0) builder.append(value).append("  ");
            else builder.append("·").append("  ");
        }
        return builder.toString();
    }

    private int convert2Dto1D(int x, int y) {
        return (y * 9) + x;
    }

    private Cell get(int x, int y) {
        return grid[convert2Dto1D(x, y)];
    }

    private int getBlock(int x, int y) {
        int blockX = x / 3;
        int blockY = y / 3;
        return blockX+blockY;
    }

    private int getX(int i) {
        return i % 9;
    }

    private int getY(int i) {
        return i / 9;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Cell {
        private final boolean mutable;
        private final int value;
    }
}
