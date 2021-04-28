package step4.domain;

import step4.domain.state.Spare;
import step4.domain.state.State;
import step4.domain.state.Strike;

public enum Symbol {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public static String of(State state, PinCount pinCount, boolean isFirst) {

        if (state instanceof Strike) {
            return STRIKE.symbol;
        }

        if (!isFirst && state instanceof Spare) {
            return SPARE.symbol;
        }

        if (pinCount.isGutter()) {
            return GUTTER.symbol;
        }

        int value = pinCount.value();
        return String.valueOf(value);
    }

    public String mark() {
        return symbol;
    }
}