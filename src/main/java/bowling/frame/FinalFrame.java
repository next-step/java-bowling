package bowling.frame;

import bowling.frame.state.Set;
import bowling.frame.state.State;
import bowling.score.Pin;

import java.util.LinkedList;

public class FinalFrame extends Frame {

    public static final int FINISHED_FRAME_NUMBER = 11;

    private final LinkedList<State> states = new LinkedList<>();

    private FinalFrame() {
        super(FINAL_FRAME_NUMBER);
        states.add(this.state);
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    protected Frame bowl(String fellPins) {
        State currentState = states.getLast();
         Pin pin = Pin.bowl(fellPins);

        if (currentState.isFinish()) {
            states.add(Set.init().bowl(pin));
            return this;
        }
        states.removeLast();
        states.add(currentState.bowl(pin));
        return this;
    }

    @Override
    public boolean isFinish() {
        State lastState = states.getLast();
        return lastState.isFinish();
    }

    @Override
    public State getState() {
        return this.states.getLast();
    }

}
