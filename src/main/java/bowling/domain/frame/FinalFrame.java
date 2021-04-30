package bowling.domain.frame;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.BowlingSymbol;
import bowling.domain.state.State;
import bowling.domain.state.progress.Ready;

public class FinalFrame implements Frame {
    private final static int MAX_TRY_COUNT = 3;

    private final List<State> states = new ArrayList<>();
    private int tryCount = 0;

    private FinalFrame() {
        states.add(new Ready());
    }

    public static Frame init() {
        return new FinalFrame();
    }

    private State currentFrame() {
        return states.get(states.size() - 1);
    }

    @Override
    public void bowl(BowlingPin bowlingPin) {
        State state = currentFrame().bowl(bowlingPin);
        updateStates(state);
        updateTryCount();
        addFrame();
    }

    private void updateStates(State state) {
        this.states.remove(states.size() - 1);
        this.states.add(state);
    }

    private void updateTryCount() {
        this.tryCount += 1;
    }

    private void addFrame() {
        if (this.isBonus()) {
            this.states.add(new Ready());
        }
    }

    private boolean isBonus() {
        return this.hasClear()
            && tryCount < MAX_TRY_COUNT;
    }

    private boolean hasClear() {
        return this.states.stream()
            .anyMatch(State::isClear);
    }

    @Override
    public Frame next(int size) {
        throw new IllegalStateException("종료 되었습니다.");
    }

    @Override
    public boolean isDone() {
        if (hasClear()) {
            return this.tryCount == MAX_TRY_COUNT;
        }
        return this.states.stream()
            .allMatch(State::isDone);
    }

    @Override
    public String frameState() {
        return states.stream()
            .map(State::toSymbol)
            .collect(joining(BowlingSymbol.DELIMITER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FinalFrame that = (FinalFrame)o;
        return tryCount == that.tryCount && Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, tryCount);
    }
}
