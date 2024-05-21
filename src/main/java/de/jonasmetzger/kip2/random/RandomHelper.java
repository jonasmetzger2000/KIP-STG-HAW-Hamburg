package de.jonasmetzger.kip2.random;

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

}
