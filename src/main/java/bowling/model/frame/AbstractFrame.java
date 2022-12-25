package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.Ready;
import bowling.model.state.State;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFrame implements Frame {

    protected final List<State> states = new ArrayList<>();
    protected final int number;

    public AbstractFrame(int number) {
        this.number = number;
        states.add(new Ready());
    }

    @Override
    public void bowl(Pin pin) {
        State currentState = getCurrentState();
        State state = currentState.bowl(pin);
        states.remove(getCurrentIndex());
        states.add(state);
    }

    @Override
    public State getCurrentState() {
        return states.get(getCurrentIndex());
    }

    private int getCurrentIndex() {
        return states.size() - 1;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
