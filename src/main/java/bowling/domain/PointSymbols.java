package bowling.domain;

import bowling.domain.Pinfall;

import java.util.ArrayList;
import java.util.List;

public class PointSymbols {
    private final List<PointSymbol> symbols;

    public PointSymbols(PointSymbol symbol) {
        this();
        symbols.add(symbol);
    }

    public PointSymbols() {
        symbols = new ArrayList<>();
    }

    public PointSymbols(List<PointSymbol> symbols) {
        this.symbols = symbols;
    }

    public List<PointSymbol> symbols() {
        return symbols;
    }
}
