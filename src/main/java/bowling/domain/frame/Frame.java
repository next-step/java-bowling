package bowling.domain.frame;

import bowling.domain.state.State;

public abstract class Frame {
    protected State state;

    protected Frame(State state) {
        this.state = state;
    }
}
