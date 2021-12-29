package bowling.domain;

import static java.lang.Boolean.TRUE;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import bowling.exception.FinalFrameNextException;
import bowling.exception.NotBowlingStateException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final String DELIMITER = "|";
    private static final int DEFAULT_TRY_COUNT = 3;
    private static final int DOWN_TRY_COUNT = 1;

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
    public Frame nextFrame() {
        throw new FinalFrameNextException();
    }

    @Override
    public Round round() {
        return Round.last();
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
    public String mark() {
        return states.stream()
            .filter(this::isNotReady)
            .map(State::mark)
            .collect(Collectors.joining(DELIMITER));
    }

    private boolean isMaxPins(State state) {
        return state instanceof Strike || state instanceof Spare;
    }

    private FinalFrame nextFinalFrame() {
        FinalFrame finalFrame = new FinalFrame(states, tryCount - DOWN_TRY_COUNT);

        if (finalFrame.isMaxPins(states.peekLast()) && !finalFrame.isFinished()) {
            finalFrame.states.addLast(new Ready());
        }

        return finalFrame;
    }

    private boolean isNotReady(State state) {
        return !(state instanceof Ready);
    }

    private void validFinished() {
        if (isFinished()) {
            throw new NotBowlingStateException();
        }
    }

}
