package bowling.domain.frame.state;

public enum StateSymbol {
    GUTTER("-"),
    STRIKE("X"),
    SPARE("/");

    private final String symbol;

    StateSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
