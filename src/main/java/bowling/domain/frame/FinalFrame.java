package bowling.domain.frame;

import bowling.domain.frame.state.*;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    private final FrameNumber frameNumber;
    private States states;
    private Count count;

    public FinalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.states = new States(new ArrayList<>());
        this.count = Count.ofFirst();
    }

    @Override
    public Frame bowl(final Pins pins) {
        State state = getCurrentState();
        states.add(state.roll(pins));
        count = count.increaseFinalFrameCount();
        return this;
    }

    @Override
    public State getCurrentState() {
        if (states.isEmpty() || states.getLast().isTurnOver()) {
            return new Ready();
        }
        return states.getLast();
    }

    @Override
    public Score getScore() {
        if (states.isEmpty() || !isEnd()) {
            return Score.NOT_ADDABLE_SCORE;
        }
        return sum();
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
    public Score calculateAdditionalScore(final Score beforeScore) {
        if (states.isEmpty() || checkFinalAccumulate(states.getList(), beforeScore.getLeft())) {
            return Score.NOT_ADDABLE_SCORE;
        }

        Score totalScore = beforeScore;
        for (State state : states.getList()) {
            totalScore = totalScore.accumulate(state.getKnockOverCount());
            if (totalScore.isCompleteAccumulation()) {
                return totalScore;
            }
        }
        return beforeScore;
    }

    @Override
    public boolean isEnd() {
        return count.isBonusCount() || isPossibleTurnOver();
    }

    @Override
    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public States getStates() {
        return states;
    }

    private boolean isPossibleTurnOver() {
        return count.isNotBonusCount() && !hasBonusState();
    }

    private boolean hasBonusState() {
        return states.getList().stream().anyMatch(Strike.class::isInstance) ||
                states.getList().stream().anyMatch(Spare.class::isInstance);
    }

    private boolean checkFinalAccumulate(final List<State> states, final int leftCount) {
        return states.size() < leftCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
