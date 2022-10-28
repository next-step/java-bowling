package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private static final int TRY_COUNT_TO_STRIKE = 1;
    private static final int TRY_COUNT_TO_SPARE = 2;

    private List<Integer> downPins = new ArrayList<>();

    public List<Integer> getDownPins() {
        return downPins;
    }

    public void record(int downPinCount) {
        downPins.add(downPinCount);
    }

    public boolean isStrike() {
        return downPins.size() == TRY_COUNT_TO_STRIKE && sumOfDownPins() == RuleConfig.NUMBER_OF_PIN;
    }

    public boolean isSpare() {
        return downPins.size() == TRY_COUNT_TO_SPARE && sumOfDownPins() == RuleConfig.NUMBER_OF_PIN;
    }

    public boolean tryOver() {
        return downPins.size() == RuleConfig.PITCH_COUNT;
    }

    private int sumOfDownPins() {
        return downPins
                .stream()
                .reduce(0, Integer::sum);
    }

}
