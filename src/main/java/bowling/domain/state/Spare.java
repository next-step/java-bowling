package bowling.domain.state;

import bowling.domain.exception.CannotBowlException;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;

public class Spare implements State {
    private static final String state = "Spare";
    private final int firstPins;
    private final int secondPins;

    private Spare(int firstPins, int secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Spare of(int firstPins, int secondPins) {
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
        pins.add(Integer.valueOf(this.firstPins));
        pins.add(Integer.valueOf(this.secondPins));
        return new StateDTO(state(),pins);
    }
}
