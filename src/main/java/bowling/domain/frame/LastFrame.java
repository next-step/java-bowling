package bowling.domain.frame;

import bowling.domain.state.State;

public class LastFrame implements Frame {

    private State state;

    private LastFrame(State state) {
        this.state = state;
    }

    public static LastFrame of() {
        return new LastFrame(State.of());
    }

    @Override
    public boolean isBowlingFinish() {
        return false;
    }
}
