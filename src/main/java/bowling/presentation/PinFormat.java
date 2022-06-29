package bowling.presentation;

public enum PinFormat {
    // score
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),

    // line
    DELIMITER("|");

    private final String format;

    PinFormat(String format) {
        this.format = format;
    }

    public String format() {
        return format;
    }
}
