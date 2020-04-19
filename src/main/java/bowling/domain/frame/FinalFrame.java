package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.Optional;

public class FinalFrame extends Frame {

    public FinalFrame(final FrameNumber frameNumber) {
        super(frameNumber);
    }

    @Override
    public Frame bowl(final Pins pins) {
        State state = getCurrentState();
        states.add(state.roll(pins));
        return this;
    }

    @Override
    public boolean isEnd() {
        return isBonusGameOver() || isGeneralGameOver();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.empty();
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

    private boolean canAdditionalScore(final Score score) {
        return states.hasCalculableSize(score.getLeftBonusCount());
    }
}
