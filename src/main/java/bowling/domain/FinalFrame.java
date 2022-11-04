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

    public void bowlV2(int number) {
        state = lastState();

        if (state.isFinished()) {
            Ready ready = new Ready();
            states.add(ready.bowl(Pin.of(number)));
            return;
        }

        states.remove(states.size() - 1);
        State bowl = state.bowl(Pin.of(number));
        states.add(bowl);
    }

    @Override
    public ScoreV2 calculateAdditionalScore(ScoreV2 beforeScore) {

        return state.calculateAdditionalScore(beforeScore);
    }

    @Override
    public int getIntScore() {
        return getFirstScore().getScore();
    }

    public ScoreV2 getFirstScore() {
        ScoreV2 firstScore = states.get(0).getScore();

        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            firstScore = state.calculateAdditionalScore(firstScore);
        }

        return firstScore;
    }

    @Override
    public boolean canBowl() {
        return !getFirstScore().canCalculateScore();
    }

    private State lastState() {
        return states.get(states.size() - 1);
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
