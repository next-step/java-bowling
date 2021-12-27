package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.value.Pins;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private static final String DELIMITER = "|";
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final int MAXIMUM_SPARE_STATE_COUNT = 2;

    private final LinkedList<State> states;

    public FinalFrame() {
        this.states = new LinkedList<>(Collections.singletonList(new Ready()));
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public boolean isFrameOver() {
        return isGameOver();
    }

    @Override
    public boolean isGameOver() {
        if (states.getLast().isGameOver()) {
            return true;
        }

        if (MAXIMUM_SPARE_STATE_COUNT == states.size() && hasSpare()) {
            return true;
        }

        return MAXIMUM_PITCH_COUNT == states.size();
    }

    private boolean hasSpare() {
        return states.stream()
                .anyMatch(Spare::isSpare);

    }

    @Override
    public void bowl(Pins pins) {
        pitchOfFinalFrame(pins);
    }

    private void pitchOfFinalFrame(Pins pins) {
        State lastState = states.getLast();

        if (lastState.isFinished()) {
            State ready = new Ready();
            states.add(ready.bowl(pins));
            return;
        }

        states.removeLast();
        states.add(lastState.bowl(pins));
    }

    @Override
    public String getMark() {
        return states.stream()
                .map(State::getMark)
                .collect(Collectors.joining(DELIMITER));
    }

}
