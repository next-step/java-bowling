package bowling.domain.frame;

import bowling.domain.frame.state.Calculable;
import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private final Frame nextFrame;
    private States states;

    public NormalFrame(final FrameNumber frameNumber, final Frame nextFrame) {
        this.frameNumber = frameNumber;
        this.nextFrame = nextFrame;
        this.states = new States(new ArrayList<>());
    }

    @Override
    public Frame bowl(final Pins pins) {
        State currentState = getCurrentState();
        states.add(currentState.roll(pins));
        if (!isEnd()) {
            return this;
        }
        return nextFrame;
    }

    @Override
    public State getCurrentState() {
        if (states.isEmpty() || states.getLast().isTurnOver()) {
            return new Ready();
        }
        return states.getLast();
    }

    @Override
    public boolean isEnd() {
        return states.getLast().isTurnOver();
    }

    @Override
    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public States getStates() {
        return states;
    }

    @Override
    public Score getScore() {
        if (states.isEmpty() || !isEnd()) {
            return Score.NOT_ADDABLE_SCORE;
        }

        Score totalScore = sum();
        if (totalScore.isCompleteAccumulation()) {
            return totalScore;
        }

        return nextFrame.calculateAdditionalScore(totalScore);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        if (states.isEmpty()) {
            return Score.NOT_ADDABLE_SCORE;
        }

        Score totalScore = beforeScore;
        for (State state : states.getList()) {
            totalScore = totalScore.accumulate(state.getKnockOverCount());
            if (totalScore.isCompleteAccumulation()) {
                return totalScore;
            }
        }
        return nextFrame.calculateAdditionalScore(totalScore);
    }

    private Score sum() {
        Score total = Score.INIT_SCORE;
        for (State state : states.getList()) {
            total = add(total, state);
        }
        return total;
    }

    private Score add(Score total, final State state) {
        if (state instanceof Calculable) {
            Score score = ((Calculable) state).getScore();
            total = total.add(score);
        }
        return total;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NormalFrame normalFrame = (NormalFrame) o;
        return Objects.equals(frameNumber, normalFrame.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
