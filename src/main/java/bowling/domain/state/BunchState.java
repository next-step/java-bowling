package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.pitching.FirstPitching;

import java.util.*;

public class BunchState extends CommonState {
    private static final int START_IDX_OF_SUB_STATES = 1;
    private static final int START_IDX_OF_STATES = 0;

    private final Stack<CommonState> states;

    protected BunchState() {
        states = new Stack<>();
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
    public Score score() {
        return subStates().stream()
                .reduce(firstState().score(),
                        (interScore, state) -> state.addScore(interScore),
                        (x, y) -> { throw new IllegalStateException(); });
    }

    @Override
    public Score addBonusScore(Score score) {
        return states.stream()
                .reduce(score,
                        (interScore, state) -> state.addScore(interScore),
                        (x, y) -> { throw new IllegalStateException(); });
    }

    private CommonState firstState() {
        return states.get(START_IDX_OF_STATES);
    }

    private List<CommonState> subStates() {
        return states.subList(START_IDX_OF_SUB_STATES, states.size());
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
        states.pop();
        states.add(state);
    }

    private CommonState lastState() {
        return states.peek();
    }

}

