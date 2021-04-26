package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.exception.CannotBowlException;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Miss implements State{
    private static final String state = "MISS";
    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Miss of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public State bowl(int pitch) {
        throw new CannotBowlException();
    }

    @Override
    public StateDTO exportStateDTO() {
        List<Integer> pins = new ArrayList<>();
        pins.add(Integer.valueOf(firstPins.pins()));
        pins.add(Integer.valueOf(secondPins.pins()));
        return new StateDTO(state(),pins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Miss)) return false;
        Miss miss = (Miss) o;
        return firstPins.equals(miss.firstPins) &&
                secondPins.equals(miss.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}
