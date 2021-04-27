package bowling.dto;

import java.util.List;

public class StateDTO {
    public final String state;
    public final List<Integer> pins;

    public StateDTO(String state, List<Integer> pins) {
        this.state = state;
        this.pins = pins;
    }

    public String state(){
        return this.state;
    }

    public List<Integer> pins(){
        return pins;
    }
}
