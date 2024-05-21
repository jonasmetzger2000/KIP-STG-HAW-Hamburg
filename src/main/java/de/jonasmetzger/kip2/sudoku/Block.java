package de.jonasmetzger.kip2.sudoku;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Block {
    private final List<Cell> cells;

    @Override
    public String toString() {
        return String.valueOf(cells.size());
    }
}
