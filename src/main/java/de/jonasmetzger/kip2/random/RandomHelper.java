package de.jonasmetzger.kip2.random;

import de.jonasmetzger.kip2.genetic.EvolutionSudoku;
import de.jonasmetzger.kip2.sudoku.Column;
import de.jonasmetzger.kip2.sudoku.Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomHelper {

    private static Random random = new Random();

    public static List<Integer> get9RandomizedInts() {
        List<Integer> randomizedInts = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9));
        Collections.shuffle(randomizedInts);
        return randomizedInts;
    }

    public static <T> T getOneFromList(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    public static int getSingleNumber() {
        return random.nextInt(9)+1;
    }

    public static double randomFloatingPoint() {
        return random.nextDouble();
    }

    public static Column getRandomColumn(Sudoku sudoku) {
        return sudoku.getColumns()[random.nextInt(sudoku.getColumns().length)];
    }

    public static Sudoku getFromList(List<Sudoku> sudokuList) {
        return sudokuList.get(random.nextInt(sudokuList.size()));
    }

    public static boolean trueOrFalse() {
        return random.nextBoolean();
    }

    public static List<EvolutionSudoku> getNRandomSudokuBoards(int n, List<EvolutionSudoku> toChooseFrom) {
        List<EvolutionSudoku> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(toChooseFrom.getFirst().clone());
            Collections.shuffle(toChooseFrom);
        }
        return result;
    }

    public static EvolutionSudoku getRandomEvolutionSudokuReserve(List<EvolutionSudoku> toChooseFrom) {
        return toChooseFrom.get(random.nextInt(toChooseFrom.size()));
    }
}
