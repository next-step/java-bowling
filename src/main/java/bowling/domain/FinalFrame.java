package bowling.domain;

import static java.lang.Boolean.TRUE;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final String DELIMITER = "|";
    private static final int DEFAULT_TRY_COUNT = 3;

    private final Deque<State> states;
    private final int tryCount;

    public FinalFrame() {
        this(new ArrayDeque<>(Collections.singletonList(new Ready())), DEFAULT_TRY_COUNT);
    }

    private FinalFrame(Deque<State> states, int tryCount) {
        this.states = states;
        this.tryCount = tryCount;
    }

    @Override
    public Frame bowl(Pins pins) {
        validFinished();
        State state = states.removeLast();
        if (isMaxPins(state)) {
            states.addLast(new Ready().bowl(pins));
            return new FinalFrame(states, tryCount - 1);
        }

        states.addLast(state.bowl(pins));
        return new FinalFrame(states, tryCount - 1);
    }

    @Override
    public String mark() {
        return states.stream()
            .map(State::mark)
            .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public boolean isFinished() {
        if (tryCount == 0) {
            return TRUE;
        }
        State state = states.peekLast();
        return state.isFinished() && !isMaxPins(state);
    }

    @Override
    public Frame nextFrame() {
        throw new RuntimeException("마지막 프레임 입니다.");
    }

    private boolean isMaxPins(State state) {
        return state instanceof Strike || state instanceof Spare;
    }

    private void validFinished() {
        if (isFinished()) {
            throw new RuntimeException("진행할 수 없습니다.");
        }
    }

}
