package bowling.state.last;

import bowling.state.State;
import bowling.state.finish.Gutter;
import bowling.state.finish.Miss;
import bowling.state.ready.First;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFrameState {

    private static final int FIRST_INDEX = 0;
    private static final int DEFAULT_VALUE = 1;

    private final List<State> states = new ArrayList<>(Collections.singleton(First.of()));
    private final LastFrameCount count = LastFrameCount.of();

    private LastFrameState() {
    }

    public static LastFrameState init() {
        return new LastFrameState();
    }

    public void bowl(int felledPins) {
        count.increase();
        bowlAndReplace(felledPins);
        if (getCurrentFrameState().isFinished()) {
            states.add(First.of());
        }
    }

    public boolean isFinished() {
        State state = states.get(FIRST_INDEX);
        return state instanceof Gutter || state instanceof Miss || count.isMax();
    }

    public String view() {
        return null;
    }

    private void bowlAndReplace(int felledPins) {
        states.set(currentCount(), getCurrentFrameState().bowl(felledPins));
    }

    private State getCurrentFrameState() {
        return states.get(currentCount());
    }

    private int currentCount() {
        return states.size() - DEFAULT_VALUE;
    }
}
