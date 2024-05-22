package de.jonasmetzger.kip2.genetic.modules;

import de.jonasmetzger.kip2.genetic.EvolutionSudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Selection {

    /**
     * Dezimiert die Population nach dem Verfahren der Tournament Selection. Ohne Zurücklegen,
     * Reduktion der Population auf 50%.
     * @param population die zur dezimierende Population
     * @return die deziemiete Population
     */
    public static List<EvolutionSudoku> tournamentSelection(List<EvolutionSudoku> population) {
        final List<EvolutionSudoku> winners = new ArrayList<>(population.size()/2);
        Collections.shuffle(population);
        for (int i = 0; i < population.size()/2; i+=2) {
            final EvolutionSudoku genom1 = population.get(i);
            final EvolutionSudoku genom2 = population.get(i+1);

            final EvolutionSudoku winner = genom1.compareTo(genom2) < 0 ? genom1 : genom2;
            winners.add(winner);
        }
        return winners;
    }
}
