package bowling.game;

public enum State {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    MISS("MISS");

    private final String symbol;

    State(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
