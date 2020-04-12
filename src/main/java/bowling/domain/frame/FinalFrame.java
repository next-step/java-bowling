package bowling.domain.frame;

import java.util.Objects;
import java.util.Optional;

public class FinalFrame implements Frame {
    private final FrameNumber frameNumber;

    public FinalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.empty();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
