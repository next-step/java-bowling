package bowling.domain.frames;

public class NormalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 2;

    public NormalFrame() {
        super();
    }

    @Override
    void checkNumberOfPins() {

        // TODO 공 합산
    }

    @Override
    public void finish() {
        if (isStrike()) {
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

    private boolean isStrike() {
        return this.scores.size() == 1 && this.scores.knockedDownPins() == NUMBER_OF_PINS;
    }
}
