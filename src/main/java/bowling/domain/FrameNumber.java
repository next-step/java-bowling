package bowling.domain;

import java.util.Objects;

public class FrameNumber {
    private int number;

    public FrameNumber(int frameNumber) {
        this.number = frameNumber;
    }

    public boolean isLastFrame() {
        return number == Frames.LAST_FRAME;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
