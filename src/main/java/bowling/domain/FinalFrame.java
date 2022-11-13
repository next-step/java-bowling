package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalFrame extends Frame {
    public static final int MAX_FRAME_NUMBER = 10;

    private final List<State> states = new ArrayList<>();

    public FinalFrame() {
        this(MAX_FRAME_NUMBER);
    }

    public FinalFrame(int number) {
        if (number != MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("마지막 프레임의 번호는 " + MAX_FRAME_NUMBER + "이어야 합니다.");
        }

        this.number = number;
        this.nextFrame = this;

        states.add(new Ready());
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

    @Override
    public boolean canBowl() {
        return !getFirstScore().canCalculateScore();
    }

    @Override
    public void bowl(int number) {
        State state = lastState();
        Pin pin = Pin.of(number);

        if (state.isFinished()) {
            states.add(new Ready().bowl(pin));
            return;
        }

        updateLastState(state.bowl(pin));
    }

    private void updateLastState(State state) {
        states.remove(states.size() - 1);
        states.add(state);
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public int getIntScore() {
        return getFirstScore().getScore();
    }

    private Score getFirstScore() {
        Score firstScore = firstState().getScore();

        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            firstScore = state.calculateAdditionalScore(firstScore);
        }

        return firstScore;
    }

    private State firstState() {
        return states.get(0);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return lastState().calculateAdditionalScore(beforeScore);
    }

    @Override
    public State getState() {
        return lastState();
    }

    private State lastState() {
        return states.get(states.size() - 1);
    }

    @Override
    public List<State> getStates() {
        return Collections.unmodifiableList(states);
    }
}
