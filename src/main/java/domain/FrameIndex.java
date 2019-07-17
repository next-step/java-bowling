package domain;

import java.util.Objects;

public class FrameIndex {
    static final String ALERT_INVALID_FRAME_NUMBER = "프레임 번호는 1부터 10까지만 허용됩니다.";
    static final int MINIMUM_FRAME_INDEX = 1;
    static final int MAXIMUM_FRAME_INDEX = 10;

    private int frameIndex;

    private FrameIndex(int frameNumber) {
        validationFrameNumber(frameNumber);
        this.frameIndex = frameNumber;
    }

    public static FrameIndex from(int frameNumber) {
        return new FrameIndex(frameNumber);
    }

    private void validationFrameNumber(int frameNumber) {
        if (frameNumber < MINIMUM_FRAME_INDEX || frameNumber > MAXIMUM_FRAME_INDEX) {
            throw new IllegalArgumentException(ALERT_INVALID_FRAME_NUMBER);
        }
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
