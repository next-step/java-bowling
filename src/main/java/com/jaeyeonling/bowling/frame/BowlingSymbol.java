package com.jaeyeonling.bowling.frame;

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
            return STRIKE.symbol;
        }
        if (knockdownPins.isMin()) {
            return GUTTER.symbol;
        }

        return String.valueOf(knockdownPins.getKnockdownPins());
    }

    static String toSymbol(final KnockdownPins firstKnockdownPins,
                           final KnockdownPins secondKnockdownPins) {
        if (firstKnockdownPins.isMax()) {
            return STRIKE.symbol;
        }

        final String symbol = toSymbol(firstKnockdownPins) + DELIMITER.symbol;
        if (secondKnockdownPins.isMax()) {
            return symbol + SPARE.symbol;
        }
        if (secondKnockdownPins.isMin()) {
            return symbol + GUTTER.symbol;
        }

        return symbol + secondKnockdownPins.getKnockdownPins();
    }
}