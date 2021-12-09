package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Arrays;
import java.util.List;

public class FinalFrame implements Frame {

    private static final int MAX_STATES_SIZE = 5;


    private FinalFrame(List<State> states) {
//        this.states = new ArrayList<>(states);
    }

    public static FinalFrame readyFrame() {
        return of(Arrays.asList(Ready.getInstance()));
    }

    public static FinalFrame of(List<State> states) {
        return new FinalFrame(states);
    }

    @Override
    public Frame bowl(Pin pin) {
//        if (isFinished()) {
//            throw new IllegalArgumentException();
//        }
//        State lastState = states.get(states.size() - 1);
//        State nextState = lastState.bowl(pin);
//        List<State> addedStates = nextStates(nextState);
//        return of(addedStates);
        return null;
    }
//
//    public boolean isFinished() {
//        if (states.size() == MAX_STATES_SIZE) {
//            return true;
//        }
//
//        State lastState = states.get(states.size() - 1);
//
//        if (lastState instanceof Miss) {
//            return true;
//        }
//
//        return false;
//    }

}
