package bowling.domain.format;

import bowling.domain.frame.state.State;

public class StateFormatter {
    private StateFormatter() {}

    public static String format(final State state) {
        return String.format(State.FORMAT, state.toResult());
    }
}
