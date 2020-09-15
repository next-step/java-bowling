package bowling.domain;

import bowling.domain.Frame;

import static bowling.common.ExceptionMessage.NORMAL_FRAME_RANGE;

public class NormalFrame extends Frame {

    private NormalRollingResult normalRollingResult = new NormalRollingResult();

    public NormalFrame(int frameIndex) {
        super(frameIndex);
    }

    @Override
    protected void validationFrameIndex(int frameIndex) {
        if (frameIndex < MIN_FRAME_INDEX || frameIndex > MAX_FRAME_INDEX - 1) {
            throw new IllegalArgumentException(NORMAL_FRAME_RANGE);
        }
    }

    @Override
    public boolean rollingEnd() {
        return normalRollingResult.isFinish();
    }

    @Override
    void bowl(int countOfPins) {
        normalRollingResult.bowl(countOfPins);
    }

    @Override
    public boolean allFrameEnd() {
        return false;
    }

    @Override
    public String index() {
        return String.valueOf(frameIndex);
    }

    @Override
    public String desc() {
        return normalRollingResult.desc();
    }
}
