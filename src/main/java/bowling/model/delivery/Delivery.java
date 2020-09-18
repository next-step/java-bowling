package bowling.model.delivery;

import bowling.model.Pins;
import bowling.model.State;

import java.util.Objects;

public class Delivery {

    private final int fallenPins;
    private final State state;

    public Delivery(int fallenPins, State state) {
        this.fallenPins = fallenPins;
        this.state = state;
    }

    public static Delivery of(int fallenPins) {
        Pins pins = new Pins();
        pins.fallingPins(fallenPins);
        return new Delivery(fallenPins, State.getStateByPins(fallenPins));
    }

    public Delivery next(int nextFallenPins) {
        Pins remainPins = Pins.remainPins(fallenPins);
        remainPins = remainPins.fallingPins(nextFallenPins);

        if (remainPins.areAllPinsFallen()) {
            return new Delivery(nextFallenPins, State.SPARE);
        }

        return new Delivery(nextFallenPins, State.getStateByPins(nextFallenPins));
    }

    public State getState() {
        return state;
    }

    public int getFallenPins() {
        return fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return fallenPins == delivery.fallenPins &&
                state == delivery.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins, state);
    }

}
