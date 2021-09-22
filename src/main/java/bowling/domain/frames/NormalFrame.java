package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.IncorrectNumberOfPinsException;

public class NormalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 2;

    public NormalFrame() {
        super();
    }

    public NormalFrame(final boolean isFinish) {
        super(isFinish);
    }

    @Override
    protected boolean isFinishFrame() {
        if (isSpare()) {
            return true;
        }
        if (isStrike()) {
            return true;
        }
        return isOverAttempts();
    }

    @Override
    public void checkValidNextScore(final Score nextScore) {
        int nextDownPins = this.scores.downPins() + nextScore.getNumberOfPins();
        if (nextDownPins > NUMBER_OF_PINS || nextDownPins < 0) {
            throw new IncorrectNumberOfPinsException();
        }
    }

    @Override
    public boolean isOverAttempts() {
        return this.scores.size() >= FRAME_MAX_ATTEMPTS;
    }
}
