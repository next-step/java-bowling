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
    public FinalFrame bowl(Pins pins) {
        validFinished();
        State state = states.removeLast().bowl(pins);
        states.addLast(state);

        return nextFinalFrame();
    }

    @Override
    public String mark() {
        return states.stream()
            .filter(state -> !(state instanceof Ready))
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

    @Override
    public Round round() {
        return Round.last();
    }

    private boolean isMaxPins(State state) {
        return state instanceof Strike || state instanceof Spare;
    }

    private FinalFrame nextFinalFrame() {
        FinalFrame finalFrame = new FinalFrame(states, tryCount - 1);

        if (finalFrame.isMaxPins(states.peekLast()) && !finalFrame.isFinished()) {
            finalFrame.states.addLast(new Ready());
        }

        return finalFrame;
    }

    private void validFinished() {
        if (isFinished()) {
            throw new RuntimeException("진행할 수 없습니다.");
        }
    }

}
