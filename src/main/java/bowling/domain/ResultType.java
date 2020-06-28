package bowling.domain;

import bowling.common.IntegerUtils;

public enum ResultType {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    public static ResultType of(Frame frame) {
        if (frame.getThrowCount() == 1 && frame.getRemain() == IntegerUtils.ZERO) {
            return STRIKE;
        }
        if (frame.getThrowCount() == Frame.MAX_THROW_COUNT && frame.getRemain() == IntegerUtils.ZERO) {
            return SPARE;
        }
        if (frame.getThrowCount() == Frame.MAX_THROW_COUNT && frame.getRemain() == Frame.TOTAL_PIN_COUNT) {
            return GUTTER;
        }
        if (frame.getThrowCount() == Frame.MAX_THROW_COUNT && frame.getRemain() > IntegerUtils.ZERO) {
            return MISS;
        }
        throw new NotFinishedFrameException("Frame is not finished");
    }
}
