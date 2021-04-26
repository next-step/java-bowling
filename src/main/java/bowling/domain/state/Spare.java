package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.exception.CannotBowlException;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spare implements State {
    private static final String state = "SPARE";
    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Spare of(Pins firstPins, Pins secondPins) {
        return new Spare(firstPins, secondPins);
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
    public State bowl(int pins) {
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
        System.out.println("@@@@@@@@@@@@");
        if (this == o) return true;
        if (!(o instanceof Spare)) return false;
        Spare spare = (Spare) o;
        return firstPins.equals(spare.firstPins) &&
                secondPins.equals(spare.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}
