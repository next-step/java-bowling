package bowling.domain.state;

import bowling.domain.Pins;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.Objects;

public class Ready implements State{
    private static final String state = "Ready";
    private Ready(){}

    public static Ready create(){
        return new Ready();
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
        if(pitch == MAX_PINS){
            return Strike.create();
        }
        Pins pins = Pins.ofFirstPitch(pitch);
        return Continue.of(pins);
    }

    @Override
    public StateDTO exportStateDTO() {
        return new StateDTO(state(), new ArrayList<>());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ready)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
