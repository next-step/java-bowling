package bowling.domain;

import static bowling.domain.BowlResult.Constant.TEN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BowlResult {

    STRIKE(Arrays.asList(0), TEN, 10),//round 1, total : 10 , current : 10
    SPARE(Arrays.asList(1), TEN, 10),//round 2, total : 10 , current : 10
    GUTTER(Arrays.asList(0, 1), Constant.ZERO_TO_TEN, 0),//round -1, total : -1, current : 0
    MISS(Collections.EMPTY_LIST, Collections.EMPTY_LIST, -1),//round -1, total : -1, current: 1
    ;

    private List<Integer> turns;
    private List<Integer> sumOfDownPins;
    private int turnDownPin;

    BowlResult(List<Integer> turns, List<Integer> sumOfDownPins, int turnDownPin) {
        this.turns = turns;
        this.sumOfDownPins = sumOfDownPins;
        this.turnDownPin = turnDownPin;
    }

    public static BowlResult find(int turn, int sumOfDownPin, int turnDownPin) {
        for (BowlResult bowl : BowlResult.values()) {
            if (bowl.turns.contains(turn) && bowl.sumOfDownPins.contains(sumOfDownPin)
                && bowl.turnDownPin == turnDownPin) {
                return bowl;
            }
        }

        return BowlResult.MISS;
    }

    static final class Constant {

        static final List<Integer> ZERO_TO_TEN = IntStream.range(0, 11).boxed()
            .collect(Collectors.toList());
        static final List<Integer> TEN = Arrays.asList(10);
    }
}
