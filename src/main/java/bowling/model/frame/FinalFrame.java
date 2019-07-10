package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Miss;
import bowling.model.frame.state.None;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame extends Frame {

    private int round;
    private List<State> states;

    private FinalFrame() {
        states = new ArrayList<>();
        states.add(new None());
    }

    static Frame of() {
        return new FinalFrame();
    }

    @Override
    public void bowl(Pins downPins) {
        round++;
        State state = states.get(states.size() - 1).isFinished() ? new None() : states.get(states.size() - 1);
        state = state.bowl(downPins);
        if (states.get(states.size() - 1).isFinished()) {
            states.add(state);
        } else {
            states.set(states.size() - 1, state);
        }
    }
    @Override
    public Frame nextFrame() {
        if (isGameOver()) {
            throw new IllegalStateException("더 이상 게임을 진행할 수 없습니다.");
        }
        return this;
    }

    @Override
    public boolean isGameOver() {
        boolean existMiss = states.stream()
                .anyMatch(Miss.class::isInstance);
        return round == 3 || existMiss;
    }

    List<State> getStates() {
        return states;
    }
}