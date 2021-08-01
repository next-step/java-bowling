package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.Stack;

public class ComplexState {
    private final Stack<State> states;

    public ComplexState() {
        states = new Stack<>();
        states.add(Preparation.instance());
    }

    public static ComplexState init() {
        return new ComplexState();
    }

    public void updateLastState(DownedPins downedPins) {
        State lastState = states.pop();
        State updatedState = lastState.downPins(downedPins);

        states.add(updatedState);
    }

    public void giveExtraChange() {
        State lastState = states.peek();

        if (lastState.isClean()) {
            states.add(Preparation.instance());
        }
    }

    public boolean isEnd() {
        State lastState = states.peek();
        return lastState.isMiss();
    }
}
