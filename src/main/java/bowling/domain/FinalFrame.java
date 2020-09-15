package bowling.domain;

import bowling.domain.Frame;

import static bowling.common.ExceptionMessage.FINAL_FRAME_RANGE;

public class FinalFrame extends Frame {

    private FinalRollingResult finalRollingResult = new FinalRollingResult();

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
        return finalRollingResult.isFinish();
    }

    @Override
    void bowl(int countOfPins) {
        finalRollingResult.bowl(countOfPins);
    }

    @Override
    public boolean allFrameEnd() {
        return finalRollingResult.isFinish();
    }

    @Override
    public String index() {
        return String.valueOf(MAX_FRAME_INDEX);
    }

    @Override
    public String desc() {
        return finalRollingResult.desc();
    }
}
