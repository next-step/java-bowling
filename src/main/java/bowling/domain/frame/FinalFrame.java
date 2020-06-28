package bowling.domain.frame;

public class FinalFrame extends Frame implements FrameFactory {

    private static final int LIMIT_COUNT = 3;

    @Override
    public boolean validateLimitScore() {
        return scores.size() == LIMIT_COUNT || validateFinalFrame() ;
    }

    private boolean validateFinalFrame() {
        if (isStrike()) {
            return false;
        }
        if (isSpare()) {
            return false;
        }
        return true;
    }
}
