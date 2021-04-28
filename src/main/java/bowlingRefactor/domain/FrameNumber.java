package bowlingRefactor.domain;

import java.util.Objects;

public class FrameNumber {

    public static final int MAX_FRAME_NUMBER = 10;
    public static final int MIN_FRAME_NUMBER = 1;
    public static final String VALIDATION_FRAME_NUMBER_MESSAGE = "프레임은 1~10 사이입니다.";

    private int frameNumber;

    private FrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FrameNumber of(int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER  || frameNumber > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(VALIDATION_FRAME_NUMBER_MESSAGE);
        }
        return new FrameNumber(frameNumber);
    }

    public boolean isFinalFrame() {
        return frameNumber == MAX_FRAME_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(frameNumber);
    }
}
