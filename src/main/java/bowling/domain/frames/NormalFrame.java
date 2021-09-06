package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.AttemptsExceededException;

public class NormalFrame implements Frame {

    private static final int FRAME_MAX_ATTEMPTS = 2;

    private Scores scores;

    @Override
    public void roll(final Score score) {
        checkValidRoll(score);
        this.scores.roll(score);
    }

    private void checkValidRoll(final Score score) {
        checkValidMaxAttempts();
        checkSecondRoll(scores, score);
    }

    private void checkValidMaxAttempts() {
        if (this.scores.size() >= FRAME_MAX_ATTEMPTS) {
            throw new AttemptsExceededException();
        }
    }
}
