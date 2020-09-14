package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Last implements State {

    private static final int THIRD_COUNT = 3;

    private boolean isThird = true;
    private List<State> states = new ArrayList<>();

    @Override
    public State roll(int count) {
        if (!states.isEmpty()) {
            State last = states.get(states.size() - 1);
            if (!last.isFinish()) {
                states.remove(states.size() - 1);
                states.add(last.roll(count));

                isThird = false;
                return this;
            }
        }
        states.add(State.from(count));
        return this;
    }

    @Override
    public List<String> value() {
        return states.stream()
                .flatMap(state -> state.value().stream())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFinish() {
        if (states.size() == THIRD_COUNT) {
            return true;
        }

        if (isNotThirdCondition()) {
            return true;
        }

        return false;
    }

    private boolean isNotThirdCondition() {
        return states.size() == 2 && !isThird;
    }
}

