package bowling.domain.frame;

public class NormalFrame extends Frame implements FrameFactory {

    private static final int LIMIT_COUNT = 2;

    @Override
    public int moveNextFrame() {
        if(scores.size() == LIMIT_COUNT || isStrike()) {
            return 1;
        }
        return 0;
    }
}
