package bowling.domain;

public enum ScoreType {
    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-");

    private static final String SEPARATOR = "|";
    private String symbol;

    ScoreType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
