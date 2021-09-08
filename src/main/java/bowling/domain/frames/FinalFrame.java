package bowling.domain.frames;


import bowling.domain.Score;
import bowling.domain.exception.IncorrectNumberOfPinsException;

public class FinalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 3;

    public FinalFrame() {
        super();
    }

    @Override
    public void checkNumberOfPins(final Score score) {
        checkPossibleRoll(score);
        checkPossibleThirdRoll(score);
    }

    private void checkPossibleThirdRoll(final Score score) {
        int pins = this.scores.knockedDownPins() + score.getNumberOfPins();
        if (isFinish(pins)) {
            throw new IncorrectNumberOfPinsException();
        }
    }

    private boolean isFinish(int pins) {
        return isNotSpare(pins) && isNotTwoStrikesInARow(pins);
    }

    private boolean isNotSpare(final int pins) {
        return this.scores.size() == 2 && NUMBER_OF_PINS != pins;
    }

    private boolean isNotTwoStrikesInARow(final int pins) {
        return this.scores.size() == 2 && NUMBER_OF_PINS * 2 != pins;
    }

    @Override
    public void finish() {
        if (isPossibleToAttempts()) {
            super.isFinish = true;
        }
        if (isFinish(this.scores.knockedDownPins())) {
            super.isFinish = true;
        }
    }

    @Override
    public boolean isPossibleToAttempts() {
        return this.scores.size() == FRAME_MAX_ATTEMPTS;
    }
}
