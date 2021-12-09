package bowling.domain.frame;

import bowling.domain.state.FinalFrameState;
import bowling.domain.state.State;

import java.util.Objects;

public class FinalFrame implements Frame {

    private State state;

    private FinalFrame(State state) {
        this.state = state;
    }

    public static FinalFrame readyFrame() {
        return of(FinalFrameState.readyState());
    }

    public static FinalFrame of(State state) {
        return new FinalFrame(state);
    }

    @Override
    public Frame bowl(Pin pin) {
        if (state.isFinished()) {
            throw new IllegalArgumentException();
        }
        state = state.bowl(pin);
        return this;
    }

    @Override
    public boolean isGameEnd() {
        return isFinished();
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public boolean isEqualsRound(Frame frame) {
        return round().equals(frame.round());
    }

    @Override
    public Round round() {
        return Round.LAST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
