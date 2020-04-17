package bowling.view.format;

public class StateFormatter {
    private static final String FORMAT = "  %-3s ";

    private StateFormatter() {}

    public static String format(final String state) {
        return String.format(FORMAT, state);
    }
}
