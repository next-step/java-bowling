package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bowling.exception.CannotAddPinsException;
import bowling.exception.EndedFrameException;

public abstract class Frame {

    public static final int MIN_NORMAL_FRAME_NUMBER = 1;
    public static final int MAX_NORMAL_FRAME_NUMBER = 9;
    public static final int LAST_FRAME_NUMBER = 10;
    protected static final int ATTEMPTS_TO_BOWL = 2;
    protected static final int ATTEMPTS_TO_BOWL_WITH_BONUS = 3;
    protected static final int MAX_PINS_COUNT = 10;
    private static final int INDEX_OF_1ST_RESULT = 1;
    private static final int INDEX_OF_2ND_RESULT = 2;

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

    public boolean isLast() {
        return number == LAST_FRAME_NUMBER;
    }

    public List<PitchResult> results() {
        return Collections.unmodifiableList(results);
    }

    public int number() {
        return number;
    }

    public Score score() {
        if (!isEnd()) {
            return Score.ofUnscored();
        }
        return Score.from(totalPins());
    }

    public PitchResult firstResult() {
        return nthResult(INDEX_OF_1ST_RESULT);
    }

    public PitchResult secondResult() {
        return nthResult(INDEX_OF_2ND_RESULT);
    }

    private PitchResult nthResult(int resultIndex) {
        try {
            return results.get(resultIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            return PitchResult.of(Score.UNSCORED);
        }
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

    protected boolean hasSpare() {
        return results.stream()
                .anyMatch(PitchResult::isSpare);
    }

    protected int totalPins() {
        return results.stream()
                .mapToInt(PitchResult::fallenPins)
                .sum();
    }

}
