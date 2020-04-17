package bowling.view.format;

public class ScoreFormatter {
    private static final String FORMAT = "  %-3s ";

    private ScoreFormatter() {}

    public static String format(final int score) {

        return String.format(FORMAT, score < 0 ? "" : score);
    }

    public static String format(final String score) {
        return String.format(FORMAT, score);
    }
}
