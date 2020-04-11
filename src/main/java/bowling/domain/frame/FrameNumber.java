package bowling.domain.frame;

import java.util.Objects;

public class FrameNumber {
    private final int frameNumber;

    public FrameNumber(final int frameNumber) {
        this.frameNumber = frameNumber;
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
