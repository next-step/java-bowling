package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.*;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private final List<State> states = new ArrayList<>();
    private int roundNumber = 0;

    public FinalFrame() {
        states.add(new Ready());
    }

    @Override
    public void bowl(Pin pin) {
        roundNumber++;
        State currentState = states.get(states.size() - 1);
        State state = currentState.bowl(pin);
        states.remove(states.size() - 1);
        states.add(state);

        if (state.isFinished()) {
            states.add(new Ready());
        }
    }

    @Override
    public boolean isFinished() {
        if (roundNumber == 3) {
            return true;
        }
        if (roundNumber == 2 && states.get(0) instanceof Miss) {
            return true;
        }
        return false;
    }
}
