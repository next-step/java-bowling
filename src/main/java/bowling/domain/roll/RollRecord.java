package bowling.domain.roll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RollRecord {

    private static final String SCORE_MARKING_DELIMITER = "|";

    private final List<Roll> rolls = new ArrayList<>();

    public int size() {
        return rolls.size();
    }

    public void add(Roll roll) {
        rolls.add(roll);
    }

    public boolean isRollTwice() {
        return size() == 2;
    }

    public boolean isRollThreeTimes() {
        return size() == 3;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && rolls.get(0).isStrike();
    }

    public boolean isSpare() {
        return isRollTwice() && rolls.get(1).isSpare();
    }

    public int getCountOfPins() {
        return rolls.stream()
                .mapToInt(Roll::getCountOfKnockedPins)
                .sum();
    }

    public String getMarking() {
        return rolls.stream()
                .map(Roll::getMarking)
                .collect(Collectors.joining(SCORE_MARKING_DELIMITER));
    }
}
