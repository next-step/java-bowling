package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.Spare;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BonusFrame implements Frame {

    private static final int LAST_COUNT = 3;

    private final List<State> states = new ArrayList<>();

    private BonusFrame(State state) {
        states.add(state);
    }

    public static BonusFrame of(State state) {
        return new BonusFrame(state);
    }

    @Override
    public int getFrameNumber() {
        return FinalFrame.FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame bowl(Pins pins) {
        State state = getLast();
        if (state.isFinish()) {
            states.add(state);
            return this;
        }

        removeLast();
        states.add(state.bowl(pins));
        return this;
    }

    @Override
    public boolean isFinish() {
        return states.size() == LAST_COUNT || isSpare();
    }

    @Override
    public List<State> getState() {
        return Collections.unmodifiableList(states);
    }

    @Override
    public Score getScore() {
        Score score = states.get(0).getScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }

        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        for (State state : states) {
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    private void removeLast() {
        states.remove(states.size() - 1);
    }

    private State getLast() {
        return states.get(states.size() - 1);
    }

    private boolean isSpare() {
        return states.size() == 2 && getLast() instanceof Spare;
    }
}
