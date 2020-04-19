package bowling.domain.frame;

import bowling.domain.frame.state.*;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class FinalFrame implements Frame {
    private final FrameNumber frameNumber;
    private States states;

    public FinalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.states = new States(new ArrayList<>());
    }

    @Override
    public Frame bowl(final Pins pins) {
        State state = getCurrentState();
        states.add(state.roll(pins));
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
    public boolean isEnd() {
        return isBonusGameOver() || isGeneralGameOver();
    }

    @Override
    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.empty();
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
        return sum();
    }

    @Override
    public Score calculateAdditionalScore(final Score beforeScore) {
        if (canAdditionalScore(beforeScore)) {
            return calculate(beforeScore);
        }
        return Score.NOT_ADDABLE_SCORE;
    }

    private boolean isBonusGameOver() {
        return states.isBonusGameCount();
    }

    private boolean isGeneralGameOver() {
        return states.isGeneralGameCount() && states.hasNotBonusState();
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

    private boolean canAdditionalScore(final Score score) {
        return states.hasCalculableSize(score.getLeftBonusCount());
    }

    private Score calculate(final Score beforeScore) {
        Score totalScore = beforeScore;
        for (State state : states.getList()) {
            totalScore = totalScore.accumulate(state.getKnockOverCount());
            if (totalScore.isCompleteAccumulation()) {
                return totalScore;
            }
        }
        return totalScore;
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
