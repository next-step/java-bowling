package bowling.domain;

public enum Status {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    NUMBER("");

    public static final int GUTTER_PINS = 0;
    public static final int STRIKE_PINS = 10;

    private String symbol;

    Status(String symbol) {
        this.symbol = symbol;
    }

    public static Status first(final int countOfPins) {
        if (countOfPins == STRIKE_PINS) {
            return STRIKE;
        }
        return NUMBER;
    }

    public static Status second(final Pitch pitch, final int countOfPins) {
        if (pitch.isStatus(NUMBER) && pitch.intValue() + countOfPins == STRIKE_PINS) {
            return SPARE;
        }
        if (pitch.isStatus(NUMBER) && countOfPins == GUTTER_PINS) {
            return GUTTER;
        }
        if ((pitch.isStatus(STRIKE) || pitch.isStatus(SPARE)) && countOfPins == STRIKE_PINS) {
            return STRIKE;
        }
        return NUMBER;
    }

    public String symbol() {
        return symbol;
    }
}
