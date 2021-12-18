package bowling.model.state;

import bowling.model.Pins;

public class Ready extends Running {

    @Override
    public State bowl(int knockedDownPin) {
        Pins pin = Pins.knockedDown(knockedDownPin);
        if(pin.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pin);
    }
}
