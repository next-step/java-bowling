package bowling.domain;

public class FinalFrame extends AbstractFrame {
    public static final String FINAL_FRAME_MESSAGE = "10프레임 이후 프레임은 없습니다.";

    public FinalFrame() {
        super(new FinalKnockedPinCounts());
    }

    @Override
    public Frame addNextFrame() {
        throw new IllegalArgumentException(FINAL_FRAME_MESSAGE);
    }

    @Override
    public boolean isNinthFrame() {
        return false;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public Frame next() {
        return null;
    }
}
