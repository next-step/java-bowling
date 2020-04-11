package bowling.domain.frame;

import java.util.Objects;

public class Frame {
    private final int frameNumber;

    public Frame(final int frameNumber) {
        this.frameNumber = frameNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Frame frame = (Frame) o;
        return frameNumber == frame.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
