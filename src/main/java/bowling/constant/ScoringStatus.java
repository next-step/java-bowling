package bowling.constant;

public enum ScoringStatus {

    SPARE("/"),
    STRIKE("X"),
    NONE(""),
    EMPTY("");

    private String symbol;

    ScoringStatus(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
