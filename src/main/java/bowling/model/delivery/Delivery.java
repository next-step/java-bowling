package bowling.model.delivery;

import bowling.model.Pins;
import bowling.model.State;

import java.util.Objects;

public class Delivery {

    private final int fallenPins;
    private final State state;

    Delivery(int fallenPins, State state) {
        this.fallenPins = fallenPins;
        this.state = state;
    }

    public static Delivery of(int fallenPins) {
        Pins pins = Pins.of(fallenPins);
        return new Delivery(fallenPins, pins.getState());
    }

    public Delivery next(int nextFallenPins) {
        Pins pins = Pins.of(fallenPins);
        return new Delivery(nextFallenPins, pins.getNextState(nextFallenPins));
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
