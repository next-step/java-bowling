package bowling.domain.frame;

import java.util.Objects;

public final class FrameScore {

    private final Pin pin;
    private final FrameStatus frameStatus;

    public FrameScore() {
        this(null, null);
    }

    public FrameScore(Pin pin, FrameStatus frameStatus) {
        this.pin = pin;
        this.frameStatus = frameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameScore that = (FrameScore) o;
        return Objects.equals(pin, that.pin) && frameStatus == that.frameStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, frameStatus);
    }
}
