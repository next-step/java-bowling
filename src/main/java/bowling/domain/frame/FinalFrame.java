package bowling.domain.frame;

import bowling.domain.state.FinalFrameState;
import bowling.domain.state.State;

import java.util.Objects;

public class FinalFrame implements Frame {

    private final State state;

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
        return of(state.bowl(pin));
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
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
