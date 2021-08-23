package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalFrame implements Frame {

    private static final int FINAL_FRAME_NUMBER = 10;
    private static final int LAST_COUNT = 3;

    private final List<State> states = new ArrayList<>();

    private FinalFrame() {
        states.add(Ready.of());
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public void bowl(Pins pins) {
        State state = getLast();
        if (state.isFinish()) {
            states.add(state);
            return;
        }

        removeLast();
        states.add(state.bowl(pins));
    }

    @Override
    public boolean isFinish() {
        return isFull() || isLastClear();
    }

    @Override
    public List<State> getState() {
        return Collections.unmodifiableList(states);
    }

    private boolean isLastClear() {
        return states.size() == 1 && !getLast().isClear();
    }

    private boolean isFull() {
        return states.size() == LAST_COUNT;
    }

    private State getLast() {
        return states.get(states.size() - 1);
    }

    private void removeLast() {
        states.remove(states.size() - 1);
    }
}
