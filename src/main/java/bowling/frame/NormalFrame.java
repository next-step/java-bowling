package bowling.frame;

import bowling.dto.StateDto;
import bowling.pin.Pin;
import bowling.state.LastStateProxy;
import bowling.state.Ready;
import bowling.state.State;

import java.util.List;

public class NormalFrame implements Frame {
    public static final int LIMIT_NORMAL_FRAME = 9;

    private State state;

    private NormalFrame(final State state) {
        this.state = state;
    }

    public static NormalFrame init(final State state) {
        return new NormalFrame(state);
    }

    @Override
    public void play(final Pin pin) {
        state = state.nextPitch(pin);
    }

    @Override
    public boolean hasTurn() {
        return state.isEnd();
    }

    public void proceed(final List<Frame> frames) {
        if (hasTurn()) {
            progressNextFrame(frames);
        }
    }

    private void progressNextFrame(final List<Frame> frames) {
        if (frames.size() < LIMIT_NORMAL_FRAME) {
            frames.add(NormalFrame.init(Ready.init()));
            return;
        }
        frames.add(LastFrame.init(LastStateProxy.init()));
    }

    @Override
    public StateDto currentState() {
        return StateDto.from(state.getScore());
    }
}
