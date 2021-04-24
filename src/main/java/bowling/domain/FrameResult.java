package bowling.domain;

import java.util.Objects;

public class FrameResult {
    private final Frame frame;

    public FrameResult(Frame frame) {
        this.frame = frame;
    }

    public String mark() {
        return Mark.convert(frame.pinCounts().pinCounts());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameResult that = (FrameResult) o;
        return Objects.equals(frame, that.frame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame);
    }
}
