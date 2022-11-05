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
        this(10);
    }

    public FinalFrame(int number) {
        if (number != MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("마지막 프레임의 번호는 " + MAX_FRAME_NUMBER + "이어야 합니다.");
        }

        this.number = number;
        this.nextFrame = this;

        states.add(new Ready());
    }

    public void bowl(int number) {
        state = lastState();

        if (state.isFinished()) {
            Ready ready = new Ready();
            states.add(ready.bowl(Pin.of(number)));
            return;
        }

        removeLastState();
        state = state.bowl(Pin.of(number));
        states.add(state);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return state.calculateAdditionalScore(beforeScore);
    }

    @Override
    public int getIntScore() {
        return getFirstScore().getScore();
    }

    @Override
    public boolean canBowl() {
        return !getFirstScore().canCalculateScore();
    }

    public Score getFirstScore() {
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

    private State lastState() {
        return states.get(states.size() - 1);
    }

    private void removeLastState() {
        states.remove(states.size() - 1);
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

    public List<State> getStates() {
        return Collections.unmodifiableList(states);
    }
}
