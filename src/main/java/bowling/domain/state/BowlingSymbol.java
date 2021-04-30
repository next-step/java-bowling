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
        if (bowlingPin.isMax()) {
            return STRIKE.toString();
        }
        if (bowlingPin.isMin()) {
            return GUTTER.toString();
        }

        return String.valueOf(bowlingPin.score());
    }

    public static String of(BowlingPin firstPin, BowlingPin secondPin) {
        String symbol = of(firstPin) + DELIMITER;
        if (firstPin.sum(secondPin).isMax()) {
            return symbol + SPARE;
        }
        if (secondPin.isMin()) {
            return symbol + GUTTER;
        }
        return symbol + secondPin.score();
    }

    @Override
    public String toString() {
        return symbol;
    }
}
