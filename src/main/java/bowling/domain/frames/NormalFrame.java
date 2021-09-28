package bowling.domain.frames;

import bowling.domain.Score;

public class NormalFrame extends AbstractFrame {

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
    protected void checkValidNextScore(final Score nextScore) {
        checkIncorrectNumberOfPins(nextScore);
    }

    @Override
    protected boolean isOverAttempts() {
        return this.numberOfRoll() >= FRAME_MAX_ATTEMPTS;
    }
}
