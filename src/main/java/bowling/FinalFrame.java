package bowling;

public class FinalFrame extends AbstractFrame {
    public static final String FINAL_FRAME_MESSAGE = "10프레임 이후 프레임은 없습니다.";

    public FinalFrame() {
        super(new FinalKnockedPinCounts());
    }

    @Override
    public Frame next() {
        throw new IllegalArgumentException(FINAL_FRAME_MESSAGE);
    }

    @Override
    public boolean isBeforeFinalFrame() {
        return false;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }
}
