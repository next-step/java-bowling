package bowling.domain.state;

import sun.jvm.hotspot.gc.shared.Space;

import static bowling.Constants.MAX_FALL_PIN_COUNT;

public class Playing implements State{

    private final int falledPin;

    public Playing(int falledPin) {
        this.falledPin = falledPin;
    }

    @Override
    public State play(int newFalledPin) {
        if(falledPin + newFalledPin == MAX_FALL_PIN_COUNT) {
            return new Spare(falledPin, newFalledPin);
        }

        return new Miss(falledPin, newFalledPin);
    }
}
