package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PointSymbols {
    private final List<PointSymbol> symbols;

    public PointSymbols(PointSymbol symbol) {
        this();
        symbols.add(symbol);
    }

    public PointSymbols(PointSymbol first, PointSymbol second) {
        this();
        symbols.add(first);
        symbols.add(second);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointSymbols that = (PointSymbols) o;
        return Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbols);
    }

    public int length() {
        return symbols.size();
    }
}
