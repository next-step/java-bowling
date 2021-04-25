package bowling.domain.state;

import bowling.domain.exception.CannotBowlException;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Strike implements State{
    private static final String state = "Strike";
    private Strike(){}

    public static Strike create() {
        return new Strike();
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
        pins.add(Integer.valueOf(MAX_PINS));
        return new StateDTO(state(),pins);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Strike)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
