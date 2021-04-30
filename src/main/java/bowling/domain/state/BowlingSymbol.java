package bowling.domain.state;

public enum BowlingSymbol {
    GUTTER("-"),
    SPARE("/"),
    STRIKE("X");

    public static final String DELIMITER = "|";

    private final String symbol;

    BowlingSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public static String of(BowlingPin bowlingPin) {
        return String.valueOf(bowlingPin.score());
    }

    public static String of(BowlingPin firstPin, BowlingPin secondPin) {
        String symbol = of(firstPin) + DELIMITER;
        return symbol + secondPin.score();
    }

    @Override
    public String toString() {
        return symbol;
    }
}
