package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.AttemptsExceededException;
import bowling.domain.exception.InvalidFinalFrameFinalRollException;


public class FinalFrame implements Frame {

    private static final int FRAME_MAX_ATTEMPTS = 3;

    private Scores scores;

    @Override
    public void roll(final Score score) {
        checkValidRoll(score);
        this.scores.roll(score);
    }

    private void checkValidRoll(final Score score) {
        checkValidMaxAttempts();
        checkSecondRoll(scores, score);
        checkFinalRoll();
    }

    private void checkFinalRoll() {
        if (this.scores.size() != 2) {
            return;
        }
        Score first = this.scores.first();
        Score second = this.scores.second();

        if (first.isStrike() || second.isStrike()) {
            return;
        }
        if (isSpare(first.plus(second))) {
            return;
        }
        throw new InvalidFinalFrameFinalRollException();
    }

    private void checkValidMaxAttempts() {
        if (this.scores.size() >= FRAME_MAX_ATTEMPTS) {
            throw new AttemptsExceededException();
        }
    }
}
