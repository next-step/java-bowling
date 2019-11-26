package bowling.domain;

import java.util.List;
import java.util.Objects;

public class NormalScore implements Score {

    private List<Integer> pins;
    private List<Symbol> symbols;

    public NormalScore(List<Integer> pins, List<Symbol> symbols) {
        this.pins = pins;
        this.symbols = symbols;
    }

    @Override
    public Score ofNext(int pin) {
        addScore(pin);
        return (isSpare(pin)) ? new SpareScore(pins, symbols) : new MissScore(pins, symbols);
    }

    private void addScore(int pin) {
        Symbol symbol = Symbol.findByPins(pins.get(0), pin);

        pins.add(pin);
        symbols.add(symbol);
    }

    private boolean isSpare(int pin) {
        return (pins.get(0) + pin == 10);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<Symbol> getSymbols() {
        return symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return Objects.equals(pins, that.pins) &&
                Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, symbols);
    }
}
