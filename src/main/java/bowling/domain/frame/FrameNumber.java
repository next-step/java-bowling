package bowling.domain.frame;

import bowling.exception.FrameNumberOutOfRangeException;

import java.util.Objects;

public class FrameNumber {
    public static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 10;

    private final int frameNumber;

    public FrameNumber(final int frameNumber) {
        validRange(frameNumber);
        this.frameNumber = frameNumber;
    }

    public FrameNumber increase() {
        return new FrameNumber(frameNumber + MIN_NUMBER);
    }

    public boolean isFinal() {
        return frameNumber == MAX_NUMBER;
    }

    private void validRange(final int frameNumber) {
        if (frameNumber < MIN_NUMBER || frameNumber > MAX_NUMBER) {
            throw new FrameNumberOutOfRangeException(frameNumber);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(frameNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FrameNumber that = (FrameNumber) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
