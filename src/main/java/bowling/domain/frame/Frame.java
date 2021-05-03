package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Optional;

import bowling.domain.score.Score;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.State;
import bowling.domain.state.States;
import bowling.domain.state.progress.Ready;

public abstract class Frame {
    protected static final int TOTAL_FRAME_SIZE = 10;
    protected static final int NORMAL_STATE_SIZE = 2;

    protected final int frameNumber;
    protected final States states;

    Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.states = new States(new ArrayList<>());
    }

    public State currentState() {
        if (states.isEmpty() || states.lastState().isDone()) {
            return new Ready();
        }
        return states.lastState();
    }

    public States states() {
        return states;
    }

    public boolean isFinalFrame() {
        return frameNumber == TOTAL_FRAME_SIZE;
    }

    protected Score sumCurrentFrameScore() {
        Score total = Score.init();

        for (State state : states.toList()) {
            total = addStateScore(total, state);
        }
        return total;
    }

    private Score addStateScore(Score total, State state) {
        if (state.isDone()) {
            total = total.add(state.score());
        }
        return total;
    }

    protected Score accumulateBeforeScore(Score score) {
        Score total = score;
        for (State state : states.toList()) {
            total = total.accumulate(state.currentBowlingPin());

            if (total.isAccumulateDone()) {
                return total;
            }
        }
        return total;
    }

    public abstract Frame bowl(BowlingPin bowlingPin);

    public abstract Optional<Frame> getNext();

    public abstract boolean isDone();

    public abstract Score score();

    protected abstract Score additionalCalculate(Score score);
}
