package bowling.frame;

import bowling.dto.StateDto;
import bowling.dto.StateDtos;
import bowling.pin.Pin;
import bowling.state.LastStateProxy;
import bowling.state.State;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

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
    public StateDtos convert() {
        final LastStateProxy lastStateProxy = (LastStateProxy) state;
        return lastStateProxy.getStates().stream()
                .map(StateDto::from)
                .collect(collectingAndThen(toList(), StateDtos::from));
    }
}
