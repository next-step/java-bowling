package bowling.game;

import java.util.stream.Stream;

public enum State {
    STRIKE("X", 10),
    SPARE("/", 10),
    GUTTER("-", 0),
    MISS("MISS", -1);

    private final String symbol;
    private final int pinCount;

    State(final String symbol, final int pinCount) {
        this.symbol = symbol;
        this.pinCount = pinCount;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isStrike() {
        return this.equals(STRIKE);
    }

    public boolean isSpare() {
        return this.equals(SPARE);
    }

    public boolean isMiss() {
        return this.equals(MISS);
    }
}
