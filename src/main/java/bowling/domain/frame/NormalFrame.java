package bowling.domain.frame;

import bowling.domain.state.State;

public class NormalFrame implements Frame{
    private State state;

    private NormalFrame(State state) {
        this.state = state;
    }

    public static NormalFrame of(State state) {
        return new NormalFrame(state);
    }


}
