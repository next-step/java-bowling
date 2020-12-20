package bowling.domain;

/**
 * Created : 2020-12-16 오전 8:08
 * Developer : Seo
 */
public enum Symbol {
    STRIKE("X", new Pins(10)),
    SPARE("/", new Pins(10)),
    GUTTER("-", new Pins(0));

    private final String symbol;
    private final Pins pins;

    Symbol(String symbol, Pins pins) {
        this.symbol = symbol;
        this.pins = pins;
    }

    public String getSymbol() {
        return symbol;
    }

    public Pins getPins() {
        return pins;
    }
}
