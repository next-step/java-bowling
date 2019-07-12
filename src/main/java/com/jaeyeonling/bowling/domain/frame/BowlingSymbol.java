package com.jaeyeonling.bowling.domain.frame;

public enum BowlingSymbol {
    STRIKE("X"),
    SPARE("/"),
    DELIMITER("|"),
    GUTTER("-");

    private final String symbol;

    BowlingSymbol(final String symbol) {
        this.symbol = symbol;
    }

    static String toSymbol(final KnockdownPins knockdownPins) {
        if (knockdownPins.isMax()) {
            return STRIKE.toString();
        }
        if (knockdownPins.isGutter()) {
            return GUTTER.toString();
        }

        return String.valueOf(knockdownPins.getKnockdownPins());
    }

    static String toSymbol(final KnockdownPins firstKnockdownPins,
                           final KnockdownPins secondKnockdownPins) {
        if (firstKnockdownPins.isMax()) {
            return STRIKE.toString();
        }

        final String symbol = toSymbol(firstKnockdownPins) + DELIMITER;
        if (firstKnockdownPins.sum(secondKnockdownPins).isMax()) {
            return symbol + SPARE;
        }
        if (secondKnockdownPins.isGutter()) {
            return symbol + GUTTER;
        }

        return symbol + secondKnockdownPins.getKnockdownPins();
    }

    @Override
    public String toString() {
        return symbol;
    }
}