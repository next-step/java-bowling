package com.jaeyeonling.bowling.domain;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

public enum BowlingSymbol {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    public static final String DELIMITER = "|";

    private final String symbol;

    BowlingSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public static String toSymbolFrom(final KnockdownPins firstKnockdownPins) {
        if (isStrike(firstKnockdownPins)) {
            return STRIKE.toString();
        }
        if (isGutter(firstKnockdownPins)) {
            return GUTTER.toString();
        }

        return String.valueOf(firstKnockdownPins.getKnockdownPins());
    }

    public static String toSymbolFrom(final KnockdownPins firstKnockdownPins,
                                      final KnockdownPins secondKnockdownPins) {
        final String symbol = toSymbolFrom(firstKnockdownPins) + DELIMITER;
        if (isSpare(firstKnockdownPins, secondKnockdownPins)) {
            return symbol + SPARE;
        }
        if (isGutter(secondKnockdownPins)) {
            return symbol + GUTTER;
        }

        return symbol + secondKnockdownPins.getKnockdownPins();
    }

    @Override
    public String toString() {
        return symbol;
    }

    private static boolean isStrike(final KnockdownPins firstKnockdownPins) {
        return firstKnockdownPins.isMax();
    }

    private static boolean isGutter(final KnockdownPins knockdownPins) {
        return knockdownPins.isMin();
    }

    private static boolean isSpare(final KnockdownPins firstKnockdownPins,
                                   final KnockdownPins secondKnockdownPins) {
        return firstKnockdownPins.sum(secondKnockdownPins).isMax();
    }
}
