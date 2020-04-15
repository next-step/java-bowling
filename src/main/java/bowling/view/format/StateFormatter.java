package bowling.view.format;

import bowling.domain.frame.state.State;

public class StateFormatter {
    private StateFormatter() {}

    public static String format(final State state) {
        return String.format(State.FORMAT, state.toResult());
    }

    public static String format(final String state) {
        return String.format(State.FORMAT, state);
    }
}
