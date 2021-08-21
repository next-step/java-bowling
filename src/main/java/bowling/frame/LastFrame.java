package bowling.frame;

import bowling.dto.StateDto;
import bowling.pin.Pin;
import bowling.state.State;

public class LastFrame implements Frame {
    private State state;

    private LastFrame(final State state) {
        this.state = state;
    }

    public static LastFrame init(final State state) {
        return new LastFrame(state);
    }

    @Override
    public void play(final Pin pin) {
        state.nextPitch(pin);
    }

    @Override
    public boolean hasTurn() {
        return !state.isEnd();
    }

    @Override
    public StateDto currentState() {
        return StateDto.from(state);
    }
}
