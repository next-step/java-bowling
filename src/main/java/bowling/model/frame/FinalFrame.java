package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.*;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    public static final int MAX_TRY_NUMBER = 3;

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
        if (getFirstState() instanceof Strike || getFirstState() instanceof Spare) {
            return roundNumber == MAX_TRY_NUMBER;
        }

        return getFirstState().isFinished();
    }

    private State getFirstState() {
        return states.get(0);
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
