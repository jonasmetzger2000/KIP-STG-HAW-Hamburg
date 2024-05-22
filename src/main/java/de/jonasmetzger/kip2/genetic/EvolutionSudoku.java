package de.jonasmetzger.kip2.genetic;

import de.jonasmetzger.kip2.sudoku.Sudoku;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class EvolutionSudoku implements Comparable<EvolutionSudoku> {
    private int round;
    private int fitness;
    private Sudoku sudoku;

    @Override
    public int compareTo(EvolutionSudoku o) {
        if (this.fitness > o.fitness) {
            return 1;
        } else if (this.fitness < o.fitness) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public EvolutionSudoku clone() {
        return new EvolutionSudoku(round, fitness, sudoku.clone());
    }

    @Override
    public String toString() {
        return String.format("Round: %d%nFitness: %d%n%s---%n", round, fitness, sudoku);
    }
}
