package de.jonasmetzger.kip2.sudoku;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

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

    protected Cell clone() {
        return new Cell(mutable, value);
    }

    public boolean isSet() {
        return value != 0;
    }

    public void set(int value) {
        if (!mutable) throw new IllegalStateException();
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return value == cell.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
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
