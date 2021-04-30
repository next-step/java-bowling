package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.State;
import bowling.domain.state.progress.Ready;

public class NormalFrame implements Frame {
    public static final int NORMAL_FRAME_SIZE = 9;

    private State state;

    private NormalFrame(State state) {
        this.state = state;
    }

    public static Frame init() {
        return new NormalFrame(new Ready());
    }

    public static Frame of(State state) {
        return new NormalFrame(state);
    }

    @Override
    public void bowl(BowlingPin bowlingPin) {
        this.state = state.bowl(bowlingPin);
    }

    @Override
    public Frame next(int size) {
        if (size == NORMAL_FRAME_SIZE) {
            return FinalFrame.init();
        }
        return NormalFrame.init();
    }

    @Override
    public boolean isDone() {
        return state.isDone();
    }

    @Override
    public String frameState() {
        return state.toSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NormalFrame that = (NormalFrame)o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
