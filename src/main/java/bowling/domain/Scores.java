package bowling.domain;

import bowling.exception.NotReadyException;
import org.springframework.util.CollectionUtils;

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
        checkDownPinCountValidation(downPinCount);
        downPins.add(downPinCount);
    }

    public int getTryCount() {
        return downPins.size();
    }

    public boolean isStrike() {
        return downPins.size() == TRY_COUNT_TO_STRIKE && sumOfDownPins() == RuleConfig.NUMBER_OF_PIN;
    }

    public boolean isSpare() {
        return downPins.size() == TRY_COUNT_TO_SPARE && sumOfDownPins() == RuleConfig.NUMBER_OF_PIN;
    }

    public boolean isTryOver() {
        return downPins.size() >= RuleConfig.PITCH_COUNT;
    }

    public boolean isValid() {
        return downPins.size() != 0;
    }

    public int sumOfDownPins() {
        return downPins
                .stream()
                .reduce(0, Integer::sum);
    }

    public int getPinScore() {
        if (!isStrike() && downPins.size() != RuleConfig.PITCH_COUNT) {
            throw new NotReadyException("Down Pins Collection size unequal pitch count (NoSTRIKE)");
        }
        return sumOfDownPins();
    }

    public int getFirstScore() {
        if (CollectionUtils.isEmpty(downPins)) {
            throw new NotReadyException("DownPins Collection is Empty");
        }
        return downPins.get(0);
    }

    public int getSecondPitchScore() {
        if (downPins.size() < 2) {
            throw new NotReadyException("DownPins Collection is less then 2");
        }
        return downPins.get(1);
    }

    private void checkDownPinCountValidation(int downPinCount) {
        if (sumOfDownPins() + downPinCount > RuleConfig.NUMBER_OF_PIN) {
            throw new RuntimeException("Sum of down pin count must not more than 10");
        }
    }
}
