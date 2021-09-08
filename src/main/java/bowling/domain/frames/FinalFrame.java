package bowling.domain.frames;


import bowling.domain.Score;
import bowling.domain.exception.IncorrectNumberOfPinsException;

public class FinalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 3;

    public FinalFrame() {
        super();
    }

    @Override
    void checkPossibleRoll(final Score score) {
        checkPossibleSecondRoll(score);
        checkPossibleThirdRoll();
    }

    private void checkPossibleThirdRoll() {
        if (isTheScoreWrongForTheThirdRoll()) {
            throw new IncorrectNumberOfPinsException();
        }
    }

    private boolean isTheScoreWrongForTheThirdRoll() {
        if (this.scores.size() == 2) {
            return isNotAllStrike() && isNotSpare();
        }
        return false;
    }

    private boolean isNotSpare() {
        return NUMBER_OF_PINS != this.scores.knockedDownPins();
    }

    private boolean isNotAllStrike() {
        return this.scores.elements()
                .stream()
                .noneMatch(Score::isStrike);
    }

    @Override
    public void finish() {
        if (isPossibleToAttempts()) {
            super.isFinish = true;
        }
        if (isTheScoreWrongForTheThirdRoll()) {
            super.isFinish = true;
        }
    }

    @Override
    protected boolean isPossibleToAttempts() {
        return this.scores.size() == FRAME_MAX_ATTEMPTS;
    }
}
