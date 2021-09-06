package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.InvalidSecondRollException;

public interface Frame {

    int NUMBER_OF_PINS = 10;

    void roll(final Score score);

    default void checkSecondRoll(final Scores scores, final Score second) {
        if (scores.size() != 1) {
            return;
        }
        Score first = scores.first();
        if (first.isStrike()) {
            return;
        }
        if (isSpare(first.plus(second))) {
            return;
        }
        throw new InvalidSecondRollException();
    }

    default boolean isSpare(final int pins) {
        return pins == NUMBER_OF_PINS;
    }
}
