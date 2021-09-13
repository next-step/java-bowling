package bowling.domain.frames;


import bowling.domain.Score;
import bowling.domain.exception.IncorrectNumberOfPinsException;

public class FinalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 3;

    public FinalFrame() {
        super();
    }

    public FinalFrame(final boolean isFinish) {
        super(isFinish);
    }

    @Override
    void checkPossibleRoll(final Score score) {
        checkPossibleSecondRoll(score);
        checkPossibleThirdRoll();
    }

    private void checkPossibleThirdRoll() {
        if (this.scores.size() != 2) {
            return;
        }
        if (!isPossibleThirdRoll()) {
            throw new IncorrectNumberOfPinsException();
        }
    }

    @Override
    public boolean isPossibleNextRoll() {
        if (this.scores.size() <= 1) {
            return true;
        }
        if (this.scores.size() == 2) {
            return isPossibleThirdRoll();
        }
        return false;
    }

    private boolean isPossibleThirdRoll() {
        if (this.scores.size() != 2) {
            return false;
        }
        if (isStrike()) {
            return true;
        }
        if (isSpare()) {
            return true;
        }
        return false;
    }

    @Override
    public void finish() {
        if (!isPossibleToAttempts()) {
            super.isFinish = true;
        }
        if (!isPossibleNextRoll()) {
            super.isFinish = true;
        }
    }

    @Override
    protected boolean isPossibleToAttempts() {
        return this.scores.size() < FRAME_MAX_ATTEMPTS;
    }
}
