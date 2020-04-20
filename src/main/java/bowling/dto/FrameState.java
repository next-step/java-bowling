package bowling.dto;

import bowling.Pin;
import bowling.frame.BowlingFrame;
import bowling.frame.CommonBowlingFrame;
import bowling.frame.LastBowlingFrame;
import bowling.framestate.State;

import java.util.List;

public class FrameState {
    private final State state;
    private final Pin bonusPin;

    private FrameState(final State state, final Pin bonusPin) {
        this.state = state;
        this.bonusPin = bonusPin;
    }

    public static FrameState newInstance(final CommonBowlingFrame bowlingFrame) {
        return new FrameState(bowlingFrame.getState(), null);
    }

    public static FrameState newInstance(final LastBowlingFrame bowlingFrame) {
        return new FrameState(bowlingFrame.getState(), bowlingFrame.getBonusPin());
    }

    public static FrameState newInstance(final BowlingFrame bowlingFrame) {
        if (bowlingFrame instanceof CommonBowlingFrame) {
            return newInstance((CommonBowlingFrame) bowlingFrame);
        }

        return newInstance((LastBowlingFrame) bowlingFrame);
    }

    public State getState() {
        return state;
    }

    public List<Pin> getPins() {
        return state.getPins();
    }

    public Pin getBonusPin() {
        return bonusPin;
    }
}
