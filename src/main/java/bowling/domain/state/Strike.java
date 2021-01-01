package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

import static bowling.common.SymbolConstants.STRIKE;
import static bowling.domain.Score.*;

public class Strike implements Calculating {
    private final int pins = MAXIMUM_VALUE;

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
        return new Score(MAXIMUM_VALUE, TWO_TRIES);
    }

    @Override
    public Score addBonusScore(Score prevScore) {
        if(prevScore.hasOneTryLeft()) {
            return new Score(prevScore.getFallenPins() + pins, NO_TRY);
        }

        if(prevScore.hasTwoTriesLeft()) {
            return new Score(prevScore.getFallenPins() + pins, ONE_TRY);
        }

        return UNSCORED;
    }

    @Override
    public int getNumberOfTries() {
        return ONE_TRY;
    }

    @Override
    public String display() {
        return STRIKE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Strike)) return false;
        Strike strike = (Strike) o;
        return pins == strike.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
