package bowling.domain.frame;

import bowling.domain.state.State;

public class GeneralFrame extends Frame {
    protected GeneralFrame(State state) {
        super(state);
    }

    public static Object init() {
        return null;
    }
}
