package de.jonasmetzger.kip2.sudoku;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Cell {
    private boolean mutable;
    private int value;
    private Block block;
    private Column column;
    private Row row;

    public Cell(boolean mutable, int value) {
        this.mutable = mutable;
        this.value = value;
    }

    public boolean isSet() {
        return value != 0;
    }

    public void set(int value) {
        if (!mutable) throw new IllegalStateException();
        this.value = value;
    }

    protected void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    protected void setBlock(Block block) {
        this.block = block;
    }

    protected void setColumn(Column column) {
        this.column = column;
    }

    protected void setRow(Row row) {
        this.row = row;
    }
}
