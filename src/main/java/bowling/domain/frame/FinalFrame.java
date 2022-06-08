package bowling.domain.frame;

import bowling.domain.Content;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import bowling.exception.NotCreateFrameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final int LAST_CHANCE = 1;
    private static final int TOTAL_HIT_COUNT_WITH_BONUS = 3;
    private static final String DELIMITER = "|";

    private final Content content;
    private final List<State> states = new ArrayList<>();

    public FinalFrame(Content content) {
        this.states.add(StateFactory.initialState());
        this.content = content;
    }

    @Override
    public Frame next() throws NotCreateFrameException {
        throw new NotCreateFrameException(content.frameNo());
    }

    @Override
    public void bowling(int hit) {
        if (currentState().isFinish()) {
            State state = StateFactory.nextState(this);
            states.add(state.bowl(hit));
            return;
        }
        renewState(currentState().bowl(hit));
    }

    private int lastStateIndex() {
        return states.size() - 1;
    }

    private State currentState() {
        return states.get(lastStateIndex());
    }

    private void renewState(State state) {
        states.remove(lastStateIndex());
        states.add(state);
    }

    private boolean remainBonusChance() {
        if (states.isEmpty()) {
            return false;
        }
        if (!currentState().hasBonusChance()) {
            return false;
        }
        return totalBowlingCount() < TOTAL_HIT_COUNT_WITH_BONUS;
    }

    private int totalBowlingCount() {
        return states.stream()
                .mapToInt(State::bowlingCount)
                .sum();
    }

    @Override
    public boolean isFinish() {
        return currentState().isFinish() && !remainBonusChance();
    }

    @Override
    public boolean hasLastBonusChance() {
        if (!currentState().isFinish()) {
            throw new IllegalStateException();
        }
        if (!currentState().hasBonusChance()) {
            return false;
        }
        return TOTAL_HIT_COUNT_WITH_BONUS - totalBowlingCount() == LAST_CHANCE;
    }

    @Override
    public Content content() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(content, that.content) && Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, states);
    }

    @Override
    public String toString() {
        return states.stream()
                .map(State::description)
                .collect(Collectors.joining(DELIMITER));
    }
}
