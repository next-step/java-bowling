package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;
import bowling.utils.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {
    private static final FrameNumber FINAL_FRAME_NUMBER = FrameNumber.from(10);
    private static final int SECOND_PITCH = 2;
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final int CHECK_BEFORE_SECOND_PITCH = 1;
    private static final String DELIMITER = "|";

    private final List<State> states;

    private FinalFrame() {
        this.frameNumber = FINAL_FRAME_NUMBER;
        this.framePins = FramePins.create();
        this.score = Score.init();
        this.states = new ArrayList<>(Collections.singletonList(Ready.of()));
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        validatePins(pins);

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
        framePins.addPins(pins);
    }

    private void changeBonusState(Pins pins) {
        State ready = Ready.of();
        states.add(ready.pitch(pins));
        framePins.addPins(pins);
    }

    private void validatePins(Pins pins) {
        if (!framePins.isFirstPitchStrike() && framePins.isFrameOver(CHECK_BEFORE_SECOND_PITCH)) {
            Preconditions.checkMaximumSize(framePins.calculateTotalPins() + pins.getPins(),
                                           STRIKE_OR_SPARE_COUNT, "최대 투구수를 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean isFrameOver() {
        return false;
    }

    @Override
    public boolean isFinalFrameOver() {
        if (isMiss()) {
            return true;
        }

        return isMaximumPitch();
    }

    private boolean isMiss() {
        return isSecondPitch() && framePins.calculateTotalPins() < STRIKE_OR_SPARE_COUNT;
    }

    private boolean isSecondPitch() {
        return framePins.isFrameOver(SECOND_PITCH);
    }

    private boolean isMaximumPitch() {
        return framePins.isFrameOver(MAXIMUM_PITCH_COUNT);
    }

    @Override
    public void accumulateScore() {
        score = Score.ofMiss(framePins.calculateTotalPins());
    }

    @Override
    public String mark() {
        return states.stream()
                .map(State::mark)
                .collect(Collectors.joining(DELIMITER));
    }
}
