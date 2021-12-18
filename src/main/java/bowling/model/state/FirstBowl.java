package bowling.model.state;

import bowling.model.Pins;

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
}
