package bowling.domain.frame;

public class FinalFrame extends Frame implements FrameFactory {

    private static final int LIMIT_COUNT = 3;
    private static final int FINAL_FRAME_MINIMUN_COUNT = 1;

    @Override
    public int moveNextFrame() {
        if (scores.size() == LIMIT_COUNT || validateFinalFrame()) {
            return 1;
        }
        return 0;
    }

    private boolean validateFinalFrame() {
        if (isStrike()) {
            return false;
        }
        if (isSpare()) {
            return false;
        }
        return !(scores.size() == FINAL_FRAME_MINIMUN_COUNT);
    }
}
