package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Objects;

public class Strike extends Finished {

    private final Pin pin = Pin.TEN;
    @Override
    public State bowl(Pin pin) {
        return null;
    }

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
    public int hashCode() {
        return Objects.hash(pin);
    }
}
