package bowling.state;

public enum StateSymbol {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    BLANK("");

    private final String symbol;

    StateSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
