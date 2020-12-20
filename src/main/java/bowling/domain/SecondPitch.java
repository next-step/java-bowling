package bowling.domain;

import bowling.domain.interfaces.State;

import java.util.Objects;

public class SecondPitch implements State {
    @Override
    public State bowl(Pins pins, int count) {
        pins.fall(count);
        if (pins.isAllDown()) {
            return new Spare(pins);
        }

        return new Miss(pins);
    }

    @Override
    public Pins getPins() {
        return pins;
    }

    @Override
    public Condition getCondition() {
        return Condition.PLAYING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondPitch that = (SecondPitch) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
