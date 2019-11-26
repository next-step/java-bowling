package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmptyScore implements Score {

    private List<Integer> pins = new ArrayList<>();
    private List<Symbol> symbols = new ArrayList<>();

    @Override
    public Score ofNext(int pin) {
        addScore(pin);
        return (isStrike(pin)) ? new StrikeScore(pins, symbols) : new NormalScore(pins, symbols);
    }

    private void addScore(int pin) {
        pins.add(pin);
        symbols.add(Symbol.findByPin(pin));
    }

    private boolean isStrike(int pin) {
        return pin == 10;
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
        EmptyScore that = (EmptyScore) o;
        return Objects.equals(pins, that.pins) &&
                Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, symbols);
    }
}
