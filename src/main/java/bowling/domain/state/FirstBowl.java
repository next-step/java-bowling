package bowling.domain.state;

import bowling.domain.value.Pins;

import java.util.Objects;

public class FirstBowl extends ProgressState{

    private static final String GUTTER_MARK = "-";

    private final Pins firstPins;

    public FirstBowl(Pins pins) {
        this.firstPins = pins;
    }

    @Override
    public State bowl(Pins secondPins) {

        if(firstPins.isSpare(secondPins)) {
            return new Spare(this.firstPins, secondPins);
        }

        if(secondPins.isGutter()) {
            return new Gutter(this.firstPins, secondPins);
        }

        return new Miss(this.firstPins, secondPins);
    }

    @Override
    public String getMark() {

        if(firstPins.isGutter()){
            return GUTTER_MARK;
        }

        return firstPins.count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(firstPins, firstBowl.firstPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins);
    }
}
