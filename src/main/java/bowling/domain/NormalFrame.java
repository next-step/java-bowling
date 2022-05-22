package bowling.domain;

public class NormalFrame implements Frame {
    private static final int INIT_REMAIN_NUMBER = 2;
    private final Score score = new Score(0, INIT_REMAIN_NUMBER);
    private Frame nextFrame;

    @Override
    public int bowl(int score) {
        return 0;
    }

    public void nextFrame(Frame frame) {
        this.nextFrame = frame;
    }
}
