package bowling.domain.frame;

import bowling.domain.state.Pin;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;
    private LinkedList<State> states = new LinkedList<>();

    public FinalFrame() {
        this.states.add(new Ready());
    }

    @Override
    public Frame bowl(Pin fallenPins) {
        State state = states.getLast();

        if (state.isFinish()) {
            states.add(new Ready().bowl(fallenPins));
            return this;
        }

        states.removeLast();
        states.add(state.bowl(fallenPins));
        return this;
    }

    @Override
    public Score calculateByBeforeScore(Score beforeScore) {
        Score score = beforeScore;
        for (State state : states) {
            if (state instanceof Strike) {
                score = state.calculateByBeforeScore(beforeScore);
                return score;
            }
            score = state.calculateByBeforeScore(beforeScore);
        }
        return score;
    }

    public Score getScore() {
        Score score = states.getFirst().getScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateByBeforeScore(score);
        }
        return score;
    }

    @Override
    public State getState() {
        return states.getLast();
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public void updateScore(Score score) {
        getState().updateScore(score);
    }

    private String display() {
        return states.stream()
                .map(State::display)
                .collect(Collectors.joining("|"));
    }

    @Override
    public Result getFrameResult() {
        if (!states.getLast().isFinish()) {
            return new Result(display(), -1);
        }

        try {
            return new Result(display(), getScore().getScore());
        } catch (IllegalArgumentException e) {
            return new Result(display(), -1);
        }

    }
}
