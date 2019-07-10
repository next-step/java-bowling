package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Miss;
import bowling.model.frame.state.None;
import bowling.model.frame.state.State;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame {

    private int round;
    private List<State> states;

    private FinalFrame() {
        states = new ArrayList<>();
        states.add(new None());
    }

    static FinalFrame of() {
        return new FinalFrame();
    }

    void bowl(Pins downPins) {
        round++;
        State state = states.get(states.size() - 1).isFinished() ? new None() : states.get(states.size() - 1);
        state = state.bowl(downPins);
        if (states.get(states.size() - 1).isFinished()) {
            states.add(state);
        } else {
            states.set(states.size() - 1, state);
        }
    }

    FinalFrame nextFrame() {
        if (isFinished()) {
            throw new IllegalStateException("더 이상 게임을 진행할 수 없습니다.");
        }
        return this;
    }

    boolean isFinished() {
        boolean existMiss = states.stream()
                .anyMatch(Miss.class::isInstance);
        return round == 3 || existMiss;
    }

    List<State> getStates() {
        return states;
    }
}