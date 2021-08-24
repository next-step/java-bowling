package bowling.domain.frame;

import bowling.domain.state.State;

public class LastFrame implements Frame{

    private final State state;

    public LastFrame(State state) {
        this.state = state;
    }

    public static LastFrame of(State state) {
        return new LastFrame(state);
    }
}
