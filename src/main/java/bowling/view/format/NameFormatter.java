package bowling.view.format;

public class NameFormatter {
    private static final String FORMAT = "%5s ";

    private NameFormatter() {}

    public static String format(final String name) {
        return String.format(FORMAT, name);
    }
}
