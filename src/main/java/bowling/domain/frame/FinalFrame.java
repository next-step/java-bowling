package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.Optional;

public class FinalFrame extends Frame {
    private static final int BONUS_STATES_SIZE = 3;

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
    public boolean isFinish() {
        return isFinishBonusGame() || isFinishNormalGame();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.empty();
    }

    @Override
    public Score getScore() {
        if (states.isEmpty() || !isFinish()) {
            return Score.NOT_ADDABLE_SCORE;
        }
        return sumCurrentFrameScore();
    }

    @Override
    public Score calculateAdditionalScore(final Score beforeScore) {
        if (canAdditionalScore(beforeScore)) {
            return accumulateBeforeScore(beforeScore);
        }
        return Score.NOT_ADDABLE_SCORE;
    }

    private boolean isFinishBonusGame() {
        return states.getSize() == BONUS_STATES_SIZE;
    }

    private boolean isFinishNormalGame() {
        return states.getSize() == NormalFrame.STATE_SIZE && states.hasNotBonusState();
    }

    private boolean canAdditionalScore(final Score score) {
        return states.hasCalculableSize(score.getLeftBonusCount());
    }
}
