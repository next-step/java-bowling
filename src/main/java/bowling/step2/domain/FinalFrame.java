package bowling.step2.domain;

public class FinalFrame extends Frame {
    private static final int FRAME_MAX_SIZE = 3;
    private static final int DEFAULT_FRAME_SIZE = 3;

    public Boolean isEndedOneFrame() {
        if (this.hasBonusCondition() && this.Scores().size() == FRAME_MAX_SIZE) {
            return true;
        }
        return !this.hasBonusCondition() && this.Scores().size() == DEFAULT_FRAME_SIZE;
    }

    public Boolean isFinalFrame() {
        return true;
    }
}
