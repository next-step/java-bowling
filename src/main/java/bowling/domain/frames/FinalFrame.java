package bowling.domain.frames;


public class FinalFrame extends Frame {

    private static final int FRAME_MAX_ATTEMPTS = 3;

    public FinalFrame() {
        super();
    }

    // TODO 2번째 까지 합산이 10 (스페어)가 아니면 종료
    // TODO 첫번째 두번째 20이거나

    @Override
    public void checkNumberOfPins() {

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
