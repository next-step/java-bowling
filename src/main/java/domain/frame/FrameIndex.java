package domain.frame;

import java.util.Objects;

public class FrameIndex {
    static final String ALERT_INVALID_FRAME_NUMBER = "프레임 번호는 1부터 10까지만 허용됩니다.";
    static final int MINIMUM_FRAME_INDEX = 1;
    public static final int MAXIMUM_FRAME_INDEX = 10;
    static final int SECOND_TO_LAST_INDEX = 9;
    static final int INCREMENT_AMOUNT = 1;

    private int frameIndex;

    private FrameIndex(int frameNumber) {
        validationFrameNumber(frameNumber);
        this.frameIndex = frameNumber;
    }

    public static FrameIndex from(int frameNumber) {
        return new FrameIndex(frameNumber);
    }

    boolean isSecondToLastIndex() {
        return frameIndex == SECOND_TO_LAST_INDEX;
    }

    FrameIndex increment() {
        return from(frameIndex + INCREMENT_AMOUNT);
    }

    boolean isSameIndex(Frame targetFrame) {
        return this.equals(targetFrame.getIndex());
    }

    private void validationFrameNumber(int frameNumber) {
        if (frameNumber < MINIMUM_FRAME_INDEX || frameNumber > MAXIMUM_FRAME_INDEX) {
            throw new IllegalArgumentException(ALERT_INVALID_FRAME_NUMBER);
        }
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameIndex that = (FrameIndex) o;
        return frameIndex == that.frameIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameIndex);
    }
}
