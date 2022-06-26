package bowling.domain.state;

import bowling.exception.InvalidScoreException;

import java.util.Objects;

public abstract class AbstractState implements State {
    static int MAX_COUNT_OF_PINS = 10;
    static int MIN_COUNT_OF_PINS = 0;
    static final int CALCULATE_TWICE = 2;
    static final int CALCULATE_ONCE = 1;
    static final int NO_MORE_CALCULATION = 0;

    int fallenPins;
    private String symbol;

    public AbstractState() {
    }

    AbstractState(int fallenPins, String symbol) {
        validCount(fallenPins);
        this.fallenPins = fallenPins;
        this.symbol = symbol;
    }

    private void validCount(int fallenPins) {
        if (fallenPins > MAX_COUNT_OF_PINS) {
            throw new InvalidScoreException("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
        }
    }

    public int getFallenPins() {
        return fallenPins;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractState that = (AbstractState) o;
        return fallenPins == that.fallenPins &&
                Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins, symbol);
    }
}
