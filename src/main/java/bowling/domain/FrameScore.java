package bowling.domain;

public class FrameScore {
    private final Frame frame;

    public FrameScore(Frame frame) {
        this.frame = frame;
    }

    public Score score() {
        return new Score(0);
    }

    public boolean isExistsAddCount() {
        return false;
    }
}
