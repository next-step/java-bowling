package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

import static bowling.common.SymbolConstants.SPARE;
import static bowling.domain.Score.*;

public class Spare implements Calculating {
    private final int firstPin;
    private final int secondPin;

    private Spare(int firstPin, int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Spare of(int firstPin, int secondPin) {
        return new Spare(firstPin, secondPin);
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public State bowl(int pins) {
        return NotBowled.init().bowl(pins);
    }

    @Override
    public Score calculateScore() {
        return new Score(MAXIMUM_VALUE, ONE_TRY);
    }

    @Override
    public Score addBonusScore(Score prevScore) {
        if(prevScore.hasOneTryLeft()) {
            return new Score(prevScore.getFallenPins() + firstPin, ONE_TRY);
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
        return String.valueOf(firstPin).concat(SPARE);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spare)) return false;
        Spare spare = (Spare) o;
        return firstPin == spare.firstPin &&
                secondPin == spare.secondPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }

}
