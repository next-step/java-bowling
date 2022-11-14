package bowling.step2.domain;

public class NormalFrame extends Frame {
    private static final int DEFAULT_FRAME_SIZE = 2;

    public Boolean IsEndOfOneFrame() {
        return this.Scores().size() == DEFAULT_FRAME_SIZE || this.isContainingStrike();
    }

    public Boolean isFinalFrame() {
        return false;
    }
}
