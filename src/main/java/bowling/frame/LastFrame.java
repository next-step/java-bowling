package bowling.frame;

import bowling.dto.StateDto;
import bowling.pin.Pin;
import bowling.state.End;
import bowling.state.State;

public class LastFrame implements Frame {
    private State state;

    private boolean hasTurn = true;

    private LastFrame(final State state) {
        this.state = state;
    }

    public static LastFrame init(final State state) {
        return new LastFrame(state);
    }

    @Override
    public void play(final Pin pin) {
        if (state.nextPitch(pin) instanceof End) {
            this.hasTurn = false;
        }
    }

    @Override
    public boolean hasTurn() {
        return !state.isEnd();
    }

    @Override
    public StateDto currentState() {
        return StateDto.from(state.getScore());
    }
}
