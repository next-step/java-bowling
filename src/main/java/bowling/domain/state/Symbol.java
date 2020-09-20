package bowling.domain.state;

public enum Symbol {
    Strike("X"),
    Spare("|/"),
    Gutter("-"),
    Miss("|"),
    Ready(""),
    Continue(""),
    AllGutter("-|-");

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
