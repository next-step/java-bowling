package bowling.presentation;

public enum ScoreFormat {
    // score
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),

    // line
    DELIMITER("|");

    private final String format;

    ScoreFormat(String format) {
        this.format = format;
    }

    public String format() {
        return format;
    }
}
