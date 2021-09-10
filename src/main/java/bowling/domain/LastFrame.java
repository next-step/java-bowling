package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFrame implements Frame {

    private static final int FRAME_NUMBER = 10;
    private static final int MAX_PINS_COUNT = 10;
    private static final int AVAILABLE_COUNT_WITHOUT_BONUS = 2;
    private static final int AVAILABLE_COUNT_WITH_BONUS = 3;

    private final List<PitchResult> results;

    public LastFrame() {
        this.results = new ArrayList<>();
    }

    @Override
    public void bowl(int fallenPins) {
        if (isEnd()) {
            throw new IllegalArgumentException();
        }

        if (isBonusChance()) {
            results.add(PitchResult.of(fallenPins));
            return;
        }

        if (isTotalPinsMoreThan10With(fallenPins)) {
            throw new IllegalArgumentException();
        }

        if (isSpare(fallenPins)) {
            results.add(PitchResult.spare(fallenPins));
            return;
        }

        results.add(PitchResult.of(fallenPins));
    }

    @Override
    public boolean isEnd() {
        if (hasStrike() || hasSpare()) {
            return results.size() == AVAILABLE_COUNT_WITH_BONUS;
        }
        return results.size() == AVAILABLE_COUNT_WITHOUT_BONUS;
    }

    private boolean isTotalPinsMoreThan10With(int fallenPins) {
        return totalPins() + fallenPins > MAX_PINS_COUNT;
    }

    @Override
    public List<PitchResult> results() {
        return Collections.unmodifiableList(results);
    }

    @Override
    public int number() {
        return FRAME_NUMBER;
    }

    private boolean isBonusChance() {
        return results.size() < 3 && (hasStrike() || hasSpare());
    }

    private boolean isSpare(int fallenPins) {
        return !results.isEmpty() && (totalPins() + fallenPins == MAX_PINS_COUNT);
    }

    private boolean hasStrike() {
        return results.contains(PitchResult.of(MAX_PINS_COUNT));
    }

    private boolean hasSpare() {
        return results.size() == 2 && totalPins() == MAX_PINS_COUNT;
    }

    private int totalPins() {
        return results.stream()
                .mapToInt(PitchResult::fallenPins)
                .sum();
    }

}
