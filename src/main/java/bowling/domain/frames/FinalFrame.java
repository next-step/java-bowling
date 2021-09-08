package bowling.domain.frames;


import bowling.domain.Score;
import bowling.domain.exception.IncorrectNumberOfPinsException;
import com.sun.tools.javac.util.List;

public class FinalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 3;

    public FinalFrame() {
        super();
    }

    @Override
    public void checkNumberOfPins(final Score score) {
        checkPossibleFirstOrSecondRoll(score);
        checkPossibleThirdRoll(score);
    }

    private void checkPossibleThirdRoll(final Score score) {
        if (this.scores.size() != 2) {
            return;
        }
        int pins = this.scores.knockedDownPins() + score.getNumberOfPins();
        if (!List.of(NUMBER_OF_PINS, NUMBER_OF_PINS * 2).contains(pins)) {
            throw new IncorrectNumberOfPinsException();
        }
    }

    @Override
    public void finish() {
        if (isPossibleToAttempts()) {
            super.isFinish = true;
        }
    }

    @Override
    public boolean isPossibleToAttempts() {
        return this.scores.size() == FRAME_MAX_ATTEMPTS;
    }
}
