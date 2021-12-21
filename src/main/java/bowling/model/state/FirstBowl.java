package bowling.model.state;

import bowling.model.Pins;

import java.util.Objects;

public class FirstBowl extends Running {

    private static final String GUTTER_DESC = "-";
    private final Pins firstPins;

    public FirstBowl(Pins knockedDownPin) {
        this.firstPins = knockedDownPin;
    }

    @Override
    public State bowl(Pins knockedDownPin) {
        if(knockedDownPin.isSpare(this.firstPins)) {
            return new Spare(this.firstPins, knockedDownPin);
        }
        return new Miss(this.firstPins, knockedDownPin);
    }

    @Override
    public String getDesc() {
        if(firstPins.isGutter()) {
            return GUTTER_DESC;
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
