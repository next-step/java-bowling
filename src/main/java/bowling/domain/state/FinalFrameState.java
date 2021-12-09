package bowling.domain.state;

import bowling.domain.frame.Pin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalFrameState implements State {

    private final List<State> states;

    private FinalFrameState(List<State> states) {
        this.states = new ArrayList<>(states);
    }

    public static FinalFrameState readyState() {
        return new FinalFrameState(Arrays.asList(Ready.getInstance()));
    }

    public static FinalFrameState of(List<State> states) {
        return new FinalFrameState(states);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }
}
