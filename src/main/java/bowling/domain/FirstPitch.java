package bowling.domain;

import bowling.domain.interfaces.State;

import java.util.Objects;

public class FirstPitch implements State {

    @Override
    public State bowl(int count) {
        pins.fall(count);
        if (pins.isAllDown()) {
            return new Strike(pins);
        }

        return new SecondPitch(pins);
    }

    @Override
    public Pins getPins() {
        return pins;
    }

    @Override
    public Condition getCondition() {
        return Condition.READY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstPitch that = (FirstPitch) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
