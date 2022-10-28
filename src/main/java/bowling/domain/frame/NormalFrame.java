package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private final State state;

    NormalFrame(State state) {
        this.state = state;
    }

    public static NormalFrame init() {
        return new NormalFrame(new Ready());
    }

    @Override
    public Frame bowl(FallenPin fallenPin) {
        if (isFinished()) {
            return NormalFrame.init();
        }

        return new NormalFrame(state.bowl(fallenPin));
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public List<State> getStates() {
        return List.of(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;

        NormalFrame normalFrame = (NormalFrame) o;

        return Objects.equals(state, normalFrame.state);
    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }
}
