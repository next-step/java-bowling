package com.jaeyeonling.bowling.domain.frame;

public enum BowlingType {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    public static final String DELIMITER = "|";

    private final String symbol;

    BowlingType(final String symbol) {
        this.symbol = symbol;
    }

    static String toSymbol(final KnockdownPins knockdownPins) {
        if (isStrike(knockdownPins)) {
            return STRIKE.toString();
        }
        if (isGutter(knockdownPins)) {
            return GUTTER.toString();
        }

        return String.valueOf(knockdownPins.getKnockdownPins());
    }

    static String toSymbol(final KnockdownPins firstKnockdownPins,
                           final KnockdownPins secondKnockdownPins) {
        if (isStrike(firstKnockdownPins)) {
            return STRIKE.toString();
        }

        final String symbol = toSymbol(firstKnockdownPins) + DELIMITER;
        if (isSpare(firstKnockdownPins, secondKnockdownPins)) {
            return symbol + SPARE;
        }
        if (isGutter(secondKnockdownPins)) {
            return symbol + GUTTER;
        }

        return symbol + secondKnockdownPins.getKnockdownPins();
    }

    private static boolean isStrike(KnockdownPins firstKnockdownPins) {
        return firstKnockdownPins.isMax();
    }

    private static boolean isSpare(final KnockdownPins firstKnockdownPins,
                                   final KnockdownPins secondKnockdownPins) {
        return firstKnockdownPins.sum(secondKnockdownPins).isMax();
    }

    private static boolean isGutter(KnockdownPins knockdownPins) {
        return knockdownPins.isGutter();
    }

    @Override
    public String toString() {
        return symbol;
    }
}