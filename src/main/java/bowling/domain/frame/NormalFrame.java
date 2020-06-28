package bowling.domain.frame;

public class NormalFrame extends Frame implements FrameInterface {

    private static final int LIMIT_COUNT = 2;

    @Override
    public boolean validateLimitScore() {
        return scores.size() == LIMIT_COUNT || isStrike();
    }
}
