package bowling.domain.state;

public enum Symbol {
    STRIKE("X"),
    SPARE("|/"),
    GUTTER("-"),
    MISS("");

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
