package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.state.pitching.FirstPitching;

import java.util.*;

public class BunchState implements CommonState {
    private final Deque<CommonState> states;

    protected BunchState() {
        states = new ArrayDeque<>();
        states.add(FirstPitching.of());
    }

    public static BunchState of() {
        return new BunchState();
    }

    @Override
    public CommonState hitPins(Pins pins) {
        changeLastState(lastState().hitPins(pins));
        return this;
    }

    @Override
    public boolean isFinish() {
        return lastState().isMiss();
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public boolean isAllHit() {
        return false;
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.emptyList();
    }

    @Override
    public List<CommonState> getState() {
        return new ArrayList<>(states);
    }

    public void addExtraChance() {
        if (isNotAllHit()) {
            return;
        }

        states.add(FirstPitching.of());
    }

    protected boolean isNotAllHit() {
        return !lastState().isAllHit();
    }

    private void changeLastState(CommonState state) {
        states.removeLast();
        states.add(state);
    }

    private CommonState lastState() {
        return states.getLast();
    }

}

