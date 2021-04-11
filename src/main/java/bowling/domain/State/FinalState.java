package bowling.domain.State;

import bowling.domain.frame.PinCount;

import java.util.ArrayList;
import java.util.List;

public class FinalState implements State {

    private static final String SYMBOL = "|";

    private static final int MAX_TRY_COUNT = 3;

    private final List<State> states = new ArrayList<>();

    private int tryCount;

    public FinalState() {
        states.add(new Ready());
    }

    private FinalState(List<State> states) {
        this.states.addAll(states);
    }

    @Override
    public State newState(PinCount pinCount) {
        if (isClosed()) {
            throw new IllegalStateException("이미 종료된 상태입니다.");
        }
        tryCount++;
        State lastState = lastState();
        if (lastState.isClosed()) {
            Ready ready = new Ready();
            states.add(ready);
            lastState = ready;
        }
        lastState.newState(pinCount);
        return new FinalState(states);
    }

    @Override
    public boolean isClosed() {
        if (isTryAll() || isFirstStateIsMiss()) {
            return true;
        }
        return false;
    }

    @Override
    public String stateInString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(states.get(0).stateInString());
        for (int i = 1; i <= states.size(); i++) {
            stringBuilder.append(states.get(i).stateInString())
                    .append(SYMBOL);
        }
        return stringBuilder.toString();
    }

    private boolean isFirstStateIsMiss() {
        State firstState = states.get(0);
        return firstState instanceof Miss;
    }

    private boolean isTryAll() {
        return tryCount == MAX_TRY_COUNT;
    }

    private State lastState() {
        return states.get(states.size() - 1);
    }


}
