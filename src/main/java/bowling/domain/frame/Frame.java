package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class Frame {

    private final int frameNumber;
    private State state;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    public Frame bowl(int pins) {
        state = state.bowl(pins);
        if (state.isFinish()) {
            return new Frame(frameNumber + 1);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNumber == frame.frameNumber &&
                Objects.equals(state, frame.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, state);
    }
}
