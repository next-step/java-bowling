package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Frame {

    protected static final int ATTEMPTS_TO_BOWL = 2;
    protected static final int ATTEMPTS_TO_BOWL_WITH_BONUS = 3;
    protected static final int MIN_FRAME_NUMBER = 1;
    protected static final int MAX_FRAME_NUMBER = 10;
    protected static final int MAX_PINS_COUNT = 10;

    protected final int number;
    protected final List<PitchResult> results;

    protected Frame(int number) {
        this.number = number;
        this.results = new ArrayList<>();
    }

    public void bowl(int fallenPins) {
        if (isEnd()) {
            throw new IllegalArgumentException();
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

    public boolean isEnd() {
        return hasStrike() || results.size() == ATTEMPTS_TO_BOWL;
    }

    public List<PitchResult> results() {
        return Collections.unmodifiableList(results);
    }

    public int number() {
        return number;
    }

    protected boolean isTotalPinsMoreThan10With(int fallenPins) {
        return totalPins() + fallenPins > MAX_PINS_COUNT;
    }

    protected boolean isSpare(int fallenPins) {
        return !results.isEmpty() && (totalPins() + fallenPins == MAX_PINS_COUNT);
    }

    protected boolean hasStrike() {
        return results.contains(PitchResult.of(MAX_PINS_COUNT));
    }

    protected int totalPins() {
        return results.stream()
                .mapToInt(PitchResult::fallenPins)
                .sum();
    }

}
