package bowling.domain.state;

import bowling.dto.StateDTO;

import java.util.ArrayList;

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
    public State bowl(int pins) {
        if(pins == MAX_PINS){
            return Strike.create();
        }
        return Continue.of(pins);
    }

    @Override
    public StateDTO exportStateDTO() {
        return new StateDTO(state(), new ArrayList<>());
    }
}
