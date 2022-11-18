package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFrame implements Frame {

    private final int number;
    private Frame nextFrame;

    private final List<State> states = new ArrayList<>();

    private LastFrame(final int number) {

        this.number = number;
        this.nextFrame = this;
        states.add(new Ready());
    }

    public static LastFrame ready() {

        return new LastFrame(10);
    }

    private void updateLastState(final State state) {

        states.remove(states.size() - 1);
        states.add(state);
    }

    private Score getFirstScore() {

        Score firstScore = firstState().getScore();

        for (int i = 1; i < states.size(); i++) {
            final State state = states.get(i);
            firstScore = state.calculateAdditionalScore(firstScore);
        }

        return firstScore;
    }

    private State firstState() {

        return states.get(0);
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
    public void bowl(final int number) {

        final State state = lastState();
        final Pin pin = new Pin(number);

        if (state.isFinished()) {
            states.add(new Ready().bowl(pin));
            return;
        }

        updateLastState(state.bowl(pin));
    }

    @Override
    public Frame nextFrame() {

        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public int getIntScore() {

        return getFirstScore().getScore();
    }

    @Override
    public Score calculateAdditionalScore(final Score beforeScore) {

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

    @Override
    public boolean isFinished() {

        return !canBowl();
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }
}