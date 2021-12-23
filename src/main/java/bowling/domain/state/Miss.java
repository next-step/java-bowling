package bowling.domain.state;

import bowling.domain.value.Pins;

import java.util.Objects;

public class Miss extends Finished {

    private static final String GUTTER_MARK = "-";

    private final Pins firstPin;
    private final Pins secondPin;

    public Miss(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String getMark() {

        if(isGutter()) {
            return GUTTER_MARK;
        }

        return this.secondPin.count();
    }

    private boolean isGutter() {
        return this.secondPin.isGutter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
