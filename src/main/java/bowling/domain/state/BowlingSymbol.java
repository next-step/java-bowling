package bowling.domain.state;

public enum BowlingSymbol {
    GUTTER("-"),
    SPARE("/"),
    STRIKE("X");

    private final String symbol;

    BowlingSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public static String of(BowlingPin bowlingPin) {
        if (bowlingPin.isMin()) {
            return GUTTER.toString();
        }
        return String.valueOf(bowlingPin.score());
    }

    public static String ofSpare() {
        return SPARE.toString();
    }

    public static String ofStrike() {
        return STRIKE.toString();
    }

    @Override
    public String toString() {
        return symbol;
    }
}
