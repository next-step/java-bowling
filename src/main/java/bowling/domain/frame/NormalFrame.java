package bowling.domain.frame;

import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private final Frame nextFrame;

    public NormalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.nextFrame = next();
    }

    private Frame next() {
        final FrameNumber nextFrameNumber = frameNumber.increase();
        if (nextFrameNumber.isFinal()) {
            return new FinalFrame(nextFrameNumber);
        }
        return new NormalFrame(nextFrameNumber);
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.of(nextFrame);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NormalFrame normalFrame = (NormalFrame) o;
        return Objects.equals(frameNumber, normalFrame.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
