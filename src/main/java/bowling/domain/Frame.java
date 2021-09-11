package bowling.domain;

import bowling.exception.EndedFrameException;
import bowling.exception.CannotAddPinsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Frame {

    public static final int MIN_NORMAL_FRAME_NUMBER = 1;
    public static final int MAX_NORMAL_FRAME_NUMBER = 9;
    public static final int LAST_FRAME_NUMBER = 10;
    protected static final int ATTEMPTS_TO_BOWL = 2;
    protected static final int ATTEMPTS_TO_BOWL_WITH_BONUS = 3;
    protected static final int MAX_PINS_COUNT = 10;

    protected final int number;
    protected final List<PitchResult> results;

    protected Frame(int number) {
        this.number = number;
        this.results = new ArrayList<>();
    }

    public void bowl(int fallenPins) {
        if (isEnd()) {
            throw new EndedFrameException();
        }

        if (isTotalPinsMoreThan10With(fallenPins)) {
            throw new CannotAddPinsException(totalPins(), fallenPins);
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
