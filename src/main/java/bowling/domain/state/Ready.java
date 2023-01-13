package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Objects;

public class Ready extends Running {

    public static final String READY_MESSAGE = "";

    @Override
    public Status bowl(Pin pin) {
        if (pin.isMax()) {
            return new Strike();
        }
        return new FirstPin(pin);
    }

    @Override
    public Score calculateScore(Score lastScore) {
        throw new IllegalStateException(CANNOT_CALCULATE_SCORE_ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return READY_MESSAGE;
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
