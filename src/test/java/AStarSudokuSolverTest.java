import de.jonasmetzger.kip.Client;
import de.jonasmetzger.kip.solver.SimpleAStarSodokuSolver;
import de.jonasmetzger.kip.sudoku.BoardArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AStarSudokuSolverTest {


    @Test
    void shouldNotSolveImpossibleSodoku() {
        assertThrows(RuntimeException.class, () -> {
            new SimpleAStarSodokuSolver(BoardArray.from(Client.load("sodoku1.txt"))).solve().toString();
        });
    }

    @Test
    void solveSimpleSudoku() {
        final String expected = Client.load("sodoku2.solved.txt");
        final String actual = new SimpleAStarSodokuSolver(BoardArray.from(Client.load("sodoku2.txt"))).solve().toString();

        assertEquals(expected, actual);
    }

}

