package bowling.domain.frames;


import bowling.domain.Score;

public class FinalFrame extends AbstractFrame {

    private static final int FRAME_MAX_ATTEMPTS = 3;
    private static final int SECOND_ATTEMPTS = 2;

    public FinalFrame() {
        super();
    }

    public FinalFrame(final boolean isFinish) {
        super(isFinish);
    }

    @Override
    protected boolean isFinishFrame() {
        if (isOverAttempts()) {
            return true;
        }
        if (this.numberOfRoll() == SECOND_ATTEMPTS) {
            return !isPossibleThirdRoll();
        }
        return false;
    }

    @Override
    protected void checkValidNextScore(final Score score) {
        if (!isStrike() && !isSpare()) {
            checkIncorrectNumberOfPins(score);
        }
    }

    private boolean isPossibleThirdRoll() {
        if (isStrike()) {
            return true;
        }
        return isSpare();
    }

    @Override
    protected boolean isOverAttempts() {
        return this.numberOfRoll() >= FRAME_MAX_ATTEMPTS;
    }
}
