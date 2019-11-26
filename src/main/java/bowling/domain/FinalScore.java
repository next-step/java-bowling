package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalScore implements Score {

    private List<Integer> pins = new ArrayList<>();
    private List<Symbol> symbols = new ArrayList<>();

    @Override
    public Score ofNext(int pin) {
        throw new IllegalArgumentException("unable to get next Score at Final");
    }

    @Override
    public void bowl(int pin) {
        Symbol symbol = canBeStrike() ? Symbol.findByPin(pin) : Symbol.findByPins(getLatestPin(), pin);

        pins.add(pin);
        symbols.add(symbol);
    }

    private boolean canBeStrike() {
        return symbols.size() == 0 || getLatestSymbol() == Symbol.STRIKE || getLatestSymbol() == Symbol.SPARE;
    }

    private Symbol getLatestSymbol() {
        if (symbols.size() == 0) {
            throw new IllegalArgumentException("Don't have latest symbol");
        }
        return symbols.get(symbols.size() - 1);
    }

    private int getLatestPin() {
        if (symbols.size() == 0) {
            throw new IllegalArgumentException("Don't have latest pin");
        }
        return pins.get(pins.size() - 1);
    }

    @Override
    public boolean isFinished() {
        return !(yetFinishedTwoTurn() || haveThirdTurn());
    }

    private boolean yetFinishedTwoTurn() {
        return pins.size() < 2;
    }

    private boolean haveThirdTurn() {
        return pins.size() == 2 && pins.get(0) + pins.get(1) >= 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalScore that = (FinalScore) o;
        return Objects.equals(pins, that.pins) &&
                Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, symbols);
    }

    public List<Symbol> getSymbols(){
        return this.symbols;
    }
}
