package bowling.domain;

public class FrameScore {
    private final Frame frame;

    public FrameScore(Frame frame) {
        this.frame = frame;
    }

    public Score score() {
        return frame.score();
    }

    public boolean isExistsAddCount() {
        return false;
    }
}
