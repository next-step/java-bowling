package bowling.domain.frame;

import bowling.domain.state.State;

import java.util.Objects;

public class Frame {

    private int frameNumber;
    private State state;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
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
