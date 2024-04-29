package de.jonasmetzger.kip.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class BoardArray {

    int[][] grid;
    BitSet[] rowConstraints;
    BitSet[] colConstraints;
    BitSet[][] bloConstraints;
    int setFields;

    static final BitSet EMPTY_MASK = new BitSet(9);
    static final BitSet FULL_MASK = new BitSet(9);

    public BoardArray() {
        grid = new int[9][9];
        rowConstraints = new BitSet[10];
        colConstraints = new BitSet[10];
        bloConstraints = new BitSet[3][3];
        setFields = 0;
        for (int i = 0; i < 9; i++) {
            this.rowConstraints[i] = new BitSet();
        }
        for (int i = 0; i < 9; i++) {
            this.colConstraints[i] = new BitSet();
        }
        for (int i = 0; i < 3; i++) {
            this.bloConstraints[i] = new BitSet[] { new BitSet(), new BitSet(), new BitSet() };
        }
        FULL_MASK.set(0, 9);
        setFields = 0;
    }

    private BoardArray(int[][] grid, BitSet[] rowConstraints, BitSet[] colConstraints, BitSet[][] bloConstraints, int setFields) {
        this.grid = grid;
        this.rowConstraints = rowConstraints;
        this.colConstraints = colConstraints;
        this.bloConstraints = bloConstraints;
        this.setFields = setFields;
    }

    @Override
    public BoardArray clone() {
        final int[][] grid = new int[9][9];
        final BitSet[] rowConstraints = new BitSet[9];
        final BitSet[] colConstraints = new BitSet[9];
        final BitSet[][] bloConstraints = new BitSet[3][3];

        for (int i = 0; i < grid.length; i++) {
            grid[i] = this.grid[i].clone();
        }
        for (int i = 0; i < grid.length; i++) {
            rowConstraints[i] = (BitSet) this.rowConstraints[i].clone();
        }
        for (int i = 0; i < grid.length; i++) {
            colConstraints[i] = (BitSet) this.colConstraints[i].clone();
        }
        for (int i = 0; i < 3; i++) {
            bloConstraints[i] = new BitSet[] {(BitSet) this.bloConstraints[i][0].clone(), (BitSet) this.bloConstraints[i][1].clone(), (BitSet) this.bloConstraints[i][2].clone() };
        }
        return new BoardArray(grid, rowConstraints, colConstraints.clone(), bloConstraints.clone(), setFields);
    }

    public boolean set(int value, int x, int y) {
        BitSet mask = new BitSet(10);
        mask.set(value);
        if (!rowConstraints[x].intersects(mask)) {
            if (!colConstraints[y].intersects(mask)) {
                int blockX = x / 3;
                int blockY = y / 3;
                if (!bloConstraints[blockX][blockY].intersects(mask)) {
                    rowConstraints[x].set(value);
                    colConstraints[y].set(value);
                    bloConstraints[blockX][blockY].set(value);
                    grid[x][y] = value;
                    setFields++;
                }
            }
        }
        return false;
    }

    public List<Integer> numsForCell(int x, int y) {
        if (get(x, y) != 0) return new ArrayList<>();

        final BitSet rowConstraint = rowConstraints[x];
        final BitSet colConstraint = colConstraints[y];
        final BitSet blockConstraint = bloConstraints[x/3][y/3];

        final BitSet mask = new BitSet(10);
        mask.or(rowConstraint);
        mask.or(colConstraint);
        mask.or(blockConstraint);
        mask.flip(0, 10);

        final List<Integer> numOptions = mask.stream().filter(value -> value != 0).boxed().toList();
        return numOptions;
    }

    public boolean solved() {
        return setFields == 81;
    }

    public int countSetFields() {
        return setFields;
    }

    public int get(int x, int y) {
        return grid[x][y];
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BoardArray other = (BoardArray) obj;
        return Arrays.deepEquals(this.grid, other.grid);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                int cell = get(x, y);
                if (x == 0) {
                    sb.append(cell == 0 ? "· " : cell + " ");
                } else {
                    if (x == grid.length-1) {
                        sb.append(cell == 0 ? " ·" : " " + cell);
                    } else {
                        sb.append(cell == 0 ? " · " : " " + cell + " ");
                    }
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    public static BoardArray from(String string) {
        final BoardArray board = new BoardArray();
        int x = 0;
        int y = 0;

        for (String line : string.split(System.lineSeparator())) {
            x = 0;
            for (String cell : line.split(" {2}")) {
                if (cell.trim().equals("·")) {
                    x++;
                    continue;
                }
                int value = Integer.parseInt(cell.trim());
                board.set(value, x++, y);
            }
            y++;
        }
        return board;
    }
}
