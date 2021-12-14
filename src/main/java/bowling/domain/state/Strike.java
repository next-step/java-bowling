package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;

import java.util.Objects;

public class Strike extends Finished {

    private final Pin pin = Pin.TEN;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Strike strike = (Strike) o;
        return Objects.equals(pin, strike.pin);
    }

    @Override
    public String viewString() {
        return pin.viewString();
    }

    @Override
    public Score score() {
        return Score.strike();
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
