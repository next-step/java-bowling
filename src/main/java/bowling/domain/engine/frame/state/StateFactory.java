package bowling.domain.engine.frame.state;

import bowling.domain.concrete.frame.state.Ready;

public class StateFactory {

    private StateFactory() {}

    public static State ready() {
        return new Ready();
    }

}
