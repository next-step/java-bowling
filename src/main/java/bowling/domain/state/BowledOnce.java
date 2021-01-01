package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

import static bowling.common.SymbolConstants.GUTTER;
import static bowling.domain.Score.*;

public class BowledOnce implements Calculated {
    private final int pins;

    private BowledOnce(int pins){
        this.pins = pins;
    }

    public static BowledOnce of(int pins) {
        return new BowledOnce(pins);
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public State bowl(int pins) {
        if(this.pins + pins == MAXIMUM_VALUE) {
            return Spare.of(this.pins, pins);
        }
        return Miss.of(this.pins, pins);
    }

    @Override
    public Score calculateScore() {
        return new Score(pins, ONE_TRY);
    }

    @Override
    public Score addBonusScore(Score prevScore) {
        if(prevScore.hasOneTryLeft()) {
            return new Score(prevScore.getFallenPins() + pins, NO_TRY);
        }

        return UNSCORED;
    }

    @Override
    public int getNumberOfTries() {
        return ONE_TRY;
    }

    @Override
    public String display() {
        return pins == MINIMUM_VALUE ? GUTTER : String.valueOf(pins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BowledOnce)) return false;
        BowledOnce that = (BowledOnce) o;
        return pins == that.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
