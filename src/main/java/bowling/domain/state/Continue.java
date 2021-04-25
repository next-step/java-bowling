package bowling.domain.state;

import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;

public class Continue implements State{
    private static final String state = "Continue";
    private final int pins;

    private Continue(int pins){
        this.pins = pins;
    }

    public static Continue of(int pins) {
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
    public State bowl(int pins) {
        if(this.pins + pins == MAX_PINS){
            return Spare.of(this.pins, pins);
        }
        return Miss.of(this.pins, pins);
    }

    @Override
    public StateDTO exportStateDTO() {
        List<Integer> pins = new ArrayList<>();
        pins.add(Integer.valueOf(this.pins));
        return new StateDTO(state(),pins);
    }
}
