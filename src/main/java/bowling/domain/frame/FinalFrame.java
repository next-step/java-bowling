package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.rolling.FinalRolling;
import bowling.domain.rolling.Rolling;

public class FinalFrame extends Frame {

    private final String FINAL_FRAME_RANGE = "FinalFrame의 인덱스는 10이여야합니다.";

    private Rolling finalRolling = new FinalRolling();

    public FinalFrame(int frameIndex) {
        super(frameIndex);
    }

    @Override
    protected void validationFrameIndex(int frameIndex) {
        if (frameIndex != MAX_FRAME_INDEX) {
            throw new IllegalArgumentException(FINAL_FRAME_RANGE);
        }
    }

    @Override
    public boolean rollingEnd() {
        return finalRolling.isFinish();
    }

    @Override
    void bowl(Pin pin) {
        finalRolling.bowl(pin);
    }

    @Override
    public boolean isEndAllFrame() {
        return finalRolling.isFinish();
    }

    @Override
    public String index() {
        return String.valueOf(MAX_FRAME_INDEX);
    }

    @Override
    public String currentFrameStatus() {
        return finalRolling.currentFrameStatus();
    }
}
