package bowling.dto;

import bowling.Pin;
import bowling.framestate.State;

import java.util.List;

public class FrameState {
    private final State state;
    private final Pin bonusPin;

    private FrameState(final State state, final Pin bonusPin) {
        this.state = state;
        this.bonusPin = bonusPin;
    }

    //Q1. 도메인 클래스에서 DTO 객체를 만들어도 되는지?
    public static FrameState newInstance(final State state, final Pin bonusPin) {
        return new FrameState(state, bonusPin);
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
