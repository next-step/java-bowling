package bowling.domain.frames;

import bowling.domain.Score;

public class NormalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 2;

    public NormalFrame() {
        super();
    }

    @Override
    void checkPossibleRoll(final Score score) {
        checkPossibleSecondRoll(score);
    }

    @Override
    public void finish() {
        if (isAllStrike()) {
            super.isFinish = true;
        }
        if (isPossibleToAttempts()) {
            super.isFinish = true;
        }
    }

    @Override
    public boolean isPossibleToAttempts() {
        return this.scores.size() == FRAME_MAX_ATTEMPTS;
    }
}
