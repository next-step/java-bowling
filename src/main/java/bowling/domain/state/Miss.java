package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.NoMoreChanceException;

import java.util.Objects;

import static bowling.common.SymbolConstants.GUTTER;
import static bowling.common.SymbolConstants.SYMBOL_DELIMITER;
import static bowling.domain.Score.*;

public class Miss implements Calculated{
    private final int firstPin;
    private final int secondPin;

    private Miss(int firstPin, int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Miss of(int firstPin, int secondPin) {
        if(firstPin + secondPin == MAXIMUM_VALUE) {
            throw new IllegalStateException();
        }

        return new Miss(firstPin, secondPin);
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public State bowl(int pins) {
        throw new NoMoreChanceException();
    }

    @Override
    public Score calculateScore() {
        return new Score(firstPin + secondPin, NO_TRY);
    }

    @Override
    public Score addBonusScore(Score prevScore) {
        if(prevScore.hasOneTryLeft()) {
            return new Score(prevScore.getFallenPins() + firstPin, NO_TRY);
        }

        if(prevScore.hasTwoTriesLeft()) {
            return new Score(prevScore.getFallenPins() + firstPin + secondPin, NO_TRY);
        }

        return UNSCORED;
    }

    @Override
    public int getNumberOfTries() {
        return TWO_TRIES;
    }

    @Override
    public String display() {
        String first = firstPin == MINIMUM_VALUE ? GUTTER : String.valueOf(firstPin);
        String second = secondPin == MINIMUM_VALUE ? GUTTER : String.valueOf(secondPin);

        return first.concat(SYMBOL_DELIMITER).concat(second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Miss)) return false;
        Miss miss = (Miss) o;
        return firstPin == miss.firstPin &&
                secondPin == miss.secondPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }


}
