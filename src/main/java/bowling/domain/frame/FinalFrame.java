package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Collections;
import java.util.List;

public class FinalFrame implements Frame {

    public static final int FINAL_FRAME_NUMBER = 10;

    private State state;

    private FinalFrame() {
        this.state = Ready.of();
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame bowl(Pins pins) {
        state = state.bowl(pins);
        if (isBonusFrame()) {
            return BonusFrame.of(state);
        }

        return this;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public List<State> getState() {
        return Collections.singletonList(state);
    }

    private boolean isMissOrGutter() {
        return state instanceof Miss || state instanceof Gutter;
    }

    private boolean isBonusFrame() {
        return isFinish() && !isMissOrGutter();
    }
}
