package bowling.view.format;

import bowling.domain.frame.state.State;

public class StateFormatter {
    private static final String FORMAT = "  %-3s ";
    private StateFormatter() {}

    public static String format(final State state) {
        return String.format(FORMAT, state.toResult());
    }

    public static String format(final String state) {
        return String.format(FORMAT, state);
    }
}
