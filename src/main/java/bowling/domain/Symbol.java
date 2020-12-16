package bowling.domain;

/**
 * Created : 2020-12-16 오전 8:08
 * Developer : Seo
 */
public enum Symbol {
    STRIKE("X", 10),
    SPARE("/", 10),
    GUTTER("-", 0);

    private final String symbol;
    private final int score;

    Symbol(String symbol, int score) {
        this.symbol = symbol;
        this.score = score;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }
}
