package bowling.domain.frame;

import bowling.domain.state.*;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {
    private static final FrameNumber FINAL_FRAME_NUMBER = FrameNumber.from(10);
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final String DELIMITER = "|";

    private final List<State> states;

    private FinalFrame() {
        this.score = Score.init();
        this.states = new ArrayList<>(Collections.singletonList(Ready.of()));
        this.frameNumber = FINAL_FRAME_NUMBER;
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        changeState(pins);
    }

    private void changeState(Pins pins) {
        int lastIndex = states.size() - 1;
        State lastState = states.get(lastIndex);

        if (lastState.isFinish()) {
            changeBonusState(pins);
            return;
        }

        states.set(lastIndex, lastState.pitch(pins));
    }

    private void changeBonusState(Pins pins) {
        State ready = Ready.of();
        states.add(ready.pitch(pins));
    }

    @Override
    public boolean isFrameOver() {
        return false;
    }

    @Override
    public boolean isFinalFrameOver() {
        State lastState = states.get(states.size() - 1);
        if (lastState instanceof Miss || lastState instanceof SecondGutter) {
            return true;
        }

        if (hasSpare() && states.size() == 2) {
            return true;
        }

        return MAXIMUM_PITCH_COUNT == states.size();
    }

    private boolean hasSpare() {
        return states.stream()
                .anyMatch(Spare.class::isInstance);
    }

    @Override
    public void accumulateScore() {
        int accumulatePins = states.stream()
                .map(State::calculatePins)
                .reduce(0, Integer::sum);

        score = Score.of(accumulatePins);
    }

    @Override
    public String mark() {
        return states.stream()
                .map(State::mark)
                .collect(Collectors.joining(DELIMITER));
    }
}
