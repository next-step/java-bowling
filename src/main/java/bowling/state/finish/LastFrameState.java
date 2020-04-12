package bowling.state.finish;

import bowling.state.State;
import bowling.state.ready.First;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrameState extends Finished {

    private static final int FIRST_INDEX = 0;
    private static final int DEFAULT_VALUE = 1;

    private final List<State> states = new ArrayList<>();
    private final LastFrameCount count = LastFrameCount.of();

    private LastFrameState() {
        setUp();
    }

    public static State init() {
        return new LastFrameState();
    }

    @Override
    public State bowl(int felledPins) {
        count.increase();
        if (isCurrentFrameStateFinished()) {
            setUp();
        }
        bowlAndReplace(felledPins);
        return this;
    }

    @Override
    public boolean isFinished() {
        State state = states.get(FIRST_INDEX);
        return state instanceof Gutter || state instanceof Miss || count.isMax();
    }

    @Override
    public String view() {
        return states.stream()
                .map(State::view)
                .collect(Collectors.joining(DELIMITER));
    }

    private void setUp() {
        states.add(First.of());
    }

    private boolean isCurrentFrameStateFinished() {
        return getCurrentFrameState().isFinished();
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
