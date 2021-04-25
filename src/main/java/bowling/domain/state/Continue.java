package bowling.domain.state;

import bowling.domain.Pins;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;

public class Continue implements State{
    private static final String state = "Continue";
    private final Pins pins;

    private Continue(Pins pins){
        this.pins = pins;
    }

    public static Continue of(Pins pins) {
        return new Continue(pins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public State bowl(int pitch) {
        Pins secondPins = this.pins.ofSecondPitch(pitch);
        if(secondPins.pins() == MAX_PINS){
            return Spare.of(pins, secondPins);
        }
        return Miss.of(pins, secondPins);
    }

    @Override
    public StateDTO exportStateDTO() {
        List<Integer> pins = new ArrayList<>();
        pins.add(Integer.valueOf(this.pins.pins()));
        return new StateDTO(state(),pins);
    }
}
