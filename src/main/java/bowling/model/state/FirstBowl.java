package bowling.model.state;

import bowling.model.Pins;

import java.util.Objects;

public class FirstBowl extends Running {

    private final Pins firstPins;

    public FirstBowl(Pins knockedDownPin) {
        this.firstPins = knockedDownPin;
    }

    @Override
    public State bowl(int knockedDownPin) {
        Pins secondPins = Pins.knockedDown(knockedDownPin);
        if(secondPins.isSpare(this.firstPins)) {
            return new Spare(this.firstPins, secondPins);
        }
        return new Miss(this.firstPins, secondPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(firstPins, firstBowl.firstPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins);
    }
}
