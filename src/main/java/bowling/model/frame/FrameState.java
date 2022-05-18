package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.NotThrown;
import bowling.utility.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FrameState {

    private static final NotThrown DEFAULT_STATE = NotThrown.INSTANCE;
    private static final String MARK_DELIMITER = "|";

    private final BallState state;
    private final List<BallState> bonusStates;

    private FrameState(BallState state, List<BallState> bonusStates) {
        Assert.notNull(state, "state must not be null");
        Assert.notNull(bonusStates, "bonusStates must not be null");
        this.state = state;
        this.bonusStates = bonusStates;
    }

    static FrameState init() {
        return from(DEFAULT_STATE);
    }

    static FrameState from(BallState state) {
        return new FrameState(state, Collections.emptyList());
    }

    static FrameState of(BallState state, List<BallState> bonusStates) {
        return new FrameState(state, bonusStates);
    }

    boolean isEndState() {
        return state.isEnd();
    }

    FrameState nextState(Pins pins) {
        validateState();
        return of(this.state.state(pins), bonusStates);
    }

    boolean hasRemainCount() {
        return 0 < (state.remainCount() - bonusStates.size());
    }

    FrameState addBonusPins(Pins pins) {
        List<BallState> ballStates = new ArrayList<>(bonusStates);
        ballStates.add(NotThrown.INSTANCE.state(pins));
        return of(state, ballStates);
    }

    int sumPinsCount() {
        return state.sumPinsCount() + this.bonusStates.stream()
                .map(BallState::sumPinsCount)
                .reduce(0, Integer::sum);
    }

    String mark() {
        return state.mark();
    }

    String markWithBonus() {
        List<String> marks = new ArrayList<>(Collections.singletonList(mark()));
        marks.addAll(bonusStates.stream()
                .map(BallState::mark)
                .collect(Collectors.toList()));
        return String.join(MARK_DELIMITER, marks);
    }

    private void validateState() {
        if (isEndState()) {
            throw new IllegalStateException(String.format("frame state(%s) is already end state", state));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, bonusStates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameState that = (FrameState) o;
        return Objects.equals(state, that.state) && Objects.equals(bonusStates, that.bonusStates);
    }

    @Override
    public String toString() {
        return "FrameState{" +
                "state=" + state +
                ", bonusStates=" + bonusStates +
                '}';
    }
}
