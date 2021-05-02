package bowling.domain;

public class FrameScore {
    private final Frame frame;

    public FrameScore(Frame frame) {
        this.frame = frame;
    }

    public int score() {
        return 0;
    }

    public boolean isExistsAddCount() {
        return false;
    }
}
