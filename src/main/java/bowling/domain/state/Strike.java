package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Objects;

public class Strike extends Finished {

    public static final String STRIKE_MESSAGE = "X";

    public Strike() {
    }

    @Override
    public Score score() {
        return Score.ofStrike();
    }

    @Override
    public Score calculateScore(Score lastScore) {
        return lastScore.bowl(Pin.MAX_AMOUNT);
    }

    @Override
    public String toString() {
        return STRIKE_MESSAGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
