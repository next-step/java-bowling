package bowling.domain;

public enum ResultType {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    public static ResultType of(Frame frame) {
        if (frame.getThrowCount() == 1 && frame.getRemain() == 0) {
            return STRIKE;
        }
        if (frame.getThrowCount() == 2 && frame.getRemain() == 0) {
            return SPARE;
        }
        if (frame.getThrowCount() == 2 && frame.getRemain() == Frame.TOTAL_PIN_COUNT) {
            return GUTTER;
        }
        if (frame.getThrowCount() == 2 && frame.getRemain() > 0) {
            return MISS;
        }
        throw new NotFinishedFrameException("Frame is not finished");
    }
}
