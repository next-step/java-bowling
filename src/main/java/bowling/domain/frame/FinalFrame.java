package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {
    private static final String DELIMITER = "|";
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final int MAXIMUM_SPARE_STATE_COUNT = 2;

    private final LinkedList<State> states;

    private FinalFrame() {
        this.score = Score.init();
        this.states = new LinkedList<>(Collections.singletonList(Ready.of()));
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        pitchOfFinalFrame(pins);

        calculateScore();
    }

    private void pitchOfFinalFrame(Pins pins) {
        State lastState = states.getLast();

        if (lastState.isFinish()) {
            State ready = Ready.of();
            states.add(ready.pitch(pins));
            return;
        }

        states.removeLast();
        states.add(lastState.pitch(pins));
    }

    private void calculateScore() {
        if (isGameOver()) {
            int accumulatePins = states.stream()
                    .map(State::countPins)
                    .reduce(0, Integer::sum);

            score = Score.of(accumulatePins);
        }
    }

    @Override
    public boolean isFrameOver() {
        return isGameOver();
    }

    @Override
    public boolean isGameOver() {
        if (states.getLast().isFrameOver()) {
            return true;
        }

        if (MAXIMUM_SPARE_STATE_COUNT == states.size() && hasSpare()) {
            return true;
        }

        return MAXIMUM_PITCH_COUNT == states.size();
    }

    private boolean hasSpare() {
        return states.stream()
                .anyMatch(Spare.class::isInstance);
    }

    @Override
    public String getMark() {
        return states.stream()
                .map(State::getMark)
                .collect(Collectors.joining(DELIMITER));
    }
}
