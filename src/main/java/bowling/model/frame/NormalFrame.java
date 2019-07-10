package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.None;
import bowling.model.frame.state.State;

import java.util.Objects;


public class NormalFrame extends Frame {

    private final FrameNumber frameNumber;
    private State state;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new None();
    }

    static NormalFrame ofFirst() {
        return new NormalFrame(FrameNumber.NUMBER_OF_START_FRAME);
    }

    static Frame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public void bowl(Pins downPins) {
        state = state.bowl(downPins);
    }

    @Override
    public Frame nextFrame() {
        if (state.isFinished()) {
            return generate(frameNumber.increase());
        }
        return this;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, state);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "frameNumber=" + frameNumber +
                ", state=" + state +
                '}';
    }
}