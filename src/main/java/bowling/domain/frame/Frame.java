package bowling.domain.frame;

import bowling.domain.frame.state.Calculable;
import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Frame {
    protected final FrameNumber frameNumber;
    protected States states;

    Frame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.states = new States(new ArrayList<>());
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    public States getStates() {
        return states;
    }

    public State getCurrentState() {
        if (states.isEmpty() || states.getLast().isTurnOver()) {
            return new Ready();
        }
        return states.getLast();
    }

    protected Score sum() {
        Score total = Score.INIT_SCORE;
        for (State state : states.getList()) {
            total = add(total, state);
        }
        return total;
    }

    protected Score add(Score total, final State state) {
        if (state instanceof Calculable) {
            Score score = ((Calculable) state).getScore();
            total = total.add(score);
        }
        return total;
    }

    protected Score calculate(final Score beforeScore) {
        Score totalScore = beforeScore;
        for (State state : states.getList()) {
            totalScore = totalScore.accumulate(state.getKnockOverCount());
            if (totalScore.isCompleteAccumulation()) {
                return totalScore;
            }
        }
        return totalScore;
    }

    public abstract boolean isFinish();

    public abstract Score getScore();

    abstract Frame bowl(Pins pins);

    abstract Optional<Frame> getNext();

    abstract Score calculateAdditionalScore(Score score);
}
