package bowling.domain.frames;

import bowling.domain.Score;

public class NormalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 2;

    public NormalFrame() {
        super();
    }

    public NormalFrame(final boolean isFinish) {
        super(isFinish);
    }

    @Override
    void checkPossibleRoll(final Score score) {
        checkPossibleSecondRoll(score);
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
    public boolean isPossibleNextRoll() {
        if (isSpare()) {
            return false;
        }
        if (isStrike()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPossibleToAttempts() {
        return this.scores.size() < FRAME_MAX_ATTEMPTS;
    }
}
