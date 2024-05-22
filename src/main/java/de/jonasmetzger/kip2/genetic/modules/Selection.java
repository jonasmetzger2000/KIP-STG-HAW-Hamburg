package de.jonasmetzger.kip2.genetic.modules;

import de.jonasmetzger.kip2.genetic.EvolutionSudoku;
import de.jonasmetzger.kip2.random.RandomHelper;

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
        for (int i = 0; i < population.size()/2; i += 2) {
            final EvolutionSudoku genom1 = population.get(i);
            final EvolutionSudoku genom2 = population.get(i+1);

            final EvolutionSudoku winner = genom1.compareTo(genom2) < 0 ? genom1 : genom2;
            winners.add(winner);
        }
        Collections.shuffle(winners);
        return winners;
    }

    public static List<EvolutionSudoku> rouletteWheelSelection(List<EvolutionSudoku> population) {
        final List<EvolutionSudoku> winners = new ArrayList<>();
        double populationFitness = 0;
        double populationThreshold = RandomHelper.randomFloatingPoint();
        for (EvolutionSudoku genom : population) {
            populationFitness += genom.getFitness();
        }
        for (EvolutionSudoku genom : population) {
            double fitness = genom.getFitness() / populationFitness;
            if (fitness > populationThreshold) {
                winners.add(genom);
            }
        }
        return winners;
    }
}
