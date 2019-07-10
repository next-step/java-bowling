package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Miss;
import bowling.model.frame.state.None;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private int round;
    private List<State> states;

    private FinalFrame() {
        super(FrameNumber.NUMBER_OF_FINAL_FRAME);
        states = new ArrayList<>();
        states.add(new None());
    }

    static Frame of() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(Pins downPins) {
        round++;
        State state = states.get(states.size() - 1).isFinished() ? new None() : states.get(states.size() - 1);
        state = state.bowl(downPins);
        if (states.get(states.size() - 1).isFinished()) {
            states.add(state);
        } else {
            states.set(states.size() - 1, state);
        }
        return this;
    }

    @Override
    public boolean isGameOver() {
        boolean existMiss = states.stream()
                .anyMatch(Miss.class::isInstance);
        return round == 3 || existMiss;
    }

    @Override
    public String printResult() {
        return states.stream().map(state -> state.printResult()).collect(Collectors.joining("|"));
    }

    List<State> getStates() {
        return states;
    }
}