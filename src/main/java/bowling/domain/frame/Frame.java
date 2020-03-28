package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.ReadyFinal;
import bowling.domain.state.State;

import java.util.Objects;

public class Frame {

    private final int frameNumber;
    private State state;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    public State bowl(int pins) {
        this.state = state.bowl(pins);
        return state;
    }

    public State bowlByFinal(int pins) {
        this.state = new ReadyFinal();
        this.state = state.bowl(pins);
        return state;
    }

    public State getState() {
        return state;
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNumber == frame.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
