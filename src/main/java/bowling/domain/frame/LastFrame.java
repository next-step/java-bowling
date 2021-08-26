package bowling.domain.frame;

import bowling.domain.state.State;

import java.util.List;

public class LastFrame implements Frame {

    private final List<State> state;

    public LastFrame(List<State> state) {
        this.state = state;
    }

    public List<State> getState() {
        return state;
    }
}
