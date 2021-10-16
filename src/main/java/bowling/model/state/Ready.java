package bowling.model.state;

import bowling.model.Pin;

public class Ready extends Running{
    @Override
    public State bowl(int falledPins) {
        Pin currentPin = new Pin(falledPins);

        if (currentPin.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(falledPins);
    }
}
