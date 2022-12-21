package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.Miss;
import bowling.model.state.Ready;
import bowling.model.state.State;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    public static final int THIRD = 3;
    public static final int SECOND = 2;

    private final List<State> states = new ArrayList<>();
    private int roundNumber = 0;

    public FinalFrame() {
        states.add(new Ready());
    }

    @Override
    public void bowl(Pin pin) {
        roundNumber++;
        State currentState = getCurrentState();
        State state = currentState.bowl(pin);
        states.remove(getCurrentIndex());
        states.add(state);

        if (state.isFinished()) {
            states.add(new Ready());
        }
    }

    private State getCurrentState() {
        return states.get(getCurrentIndex());
    }

    private int getCurrentIndex() {
        return states.size() - 1;
    }

    @Override
    public boolean isFinished() {
        if (roundNumber == THIRD) {
            return true;
        }
        return roundNumber == SECOND && states.get(0) instanceof Miss;
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public int getNumber() {
        return 10;
    }

    @Override
    public State getState() {
        return getCurrentState();
    }

    public List<State> getStates() {
        return states;
    }
}
