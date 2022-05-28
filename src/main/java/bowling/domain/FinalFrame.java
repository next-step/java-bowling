package bowling.domain;

import bowling.domain.state.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame{
    private final List<State> states = new ArrayList<>();

    public FinalFrame() {
        this.states.add(new Ready());
    }

    @Override
    public Frame bowl(int countOfPins) {
        State state = states.get(states.size() -1);
        if(state.isFinish() && !(state instanceof Miss)) {
            states.add(new Ready().bowl(countOfPins));
            return this;
        }

        if(state.isFinish() && state instanceof Miss) {
            return this;
        }
        states.remove(statesSize() -1);
        states.add(state.bowl(countOfPins));
        return this;
    }

    @Override
    public State getState() {
        return states.get(statesSize() -1);
    }

    @Override
    public String expression() {
        return states.stream()
                .map(State::expression)
                .collect(Collectors.joining(" | "));
    }


    private int statesSize() {
        return this.states.size();
    }

}
