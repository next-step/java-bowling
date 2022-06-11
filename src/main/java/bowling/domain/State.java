package bowling.domain;

import bowling.exception.InvalidScoreException;

import java.util.Objects;

public abstract class State {
    static final int MAX_COUNT_OF_PINS = 10;
    static final int MIN_COUNT_OF_PINS = 0;

    int countOfPins;
    String symbol;

    public State() {
    }

    public State(int countOfPins, String symbol) {
        validCount(countOfPins);

        this.countOfPins = countOfPins;
        this.symbol = symbol;
    }

    void validCount(int countOfPins) {
        if (countOfPins > MAX_COUNT_OF_PINS) {
            throw new InvalidScoreException("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
        }
    }

    private void validSecondCount(int countOfPins) {
        if (countOfPins > MAX_COUNT_OF_PINS || this.countOfPins + countOfPins > MAX_COUNT_OF_PINS) {
            throw new InvalidScoreException("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
        }
    }

    public abstract State bowl(int firstBowl);

    State bowlSecond(int secondBowl) {
        validSecondCount(secondBowl);
        if (MIN_COUNT_OF_PINS == secondBowl) {
            return new Gutter();
        }

        if (this.countOfPins + secondBowl == MAX_COUNT_OF_PINS) {
            return new Spare(this.countOfPins, secondBowl);
        }

        return new Miss(this.countOfPins, secondBowl);
    }

    public int getCountOfPins() {
        return countOfPins;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return countOfPins == state.countOfPins &&
                Objects.equals(symbol, state.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins, symbol);
    }
}
