package de.jonasmetzger.kip2.sudoku;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuTest {

    @Test
    void cloneTest() {
        Sudoku first = new Sudoku();
        Sudoku second = first.clone();

        // Cells Assertions
        for (int i = 0; i < first.getCells().length; i++) {
            Cell firstCell = first.getCells()[i];
            Cell secondCell = second.getCells()[i];

            assertThat(firstCell).isEqualTo(secondCell);
            assertThat(firstCell == secondCell).isFalse();
        }

        assertThat(first == second).isFalse();
    }
}