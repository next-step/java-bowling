package bowling.domain.frame;

import java.util.Optional;

import bowling.domain.score.Score;
import bowling.domain.state.BowlingPin;

public class FinalFrame extends Frame {
    private final static int BONUS_STATE_SIZE = 3;

    private FinalFrame(int frameNumber) {
        super(frameNumber);
    }

    public static Frame of(int frameNumber) {
        return new FinalFrame(frameNumber);
    }

    @Override
    public Frame bowl(BowlingPin bowlingPin) {
        states.add(currentState().bowl(bowlingPin));
        return this;
    }

    @Override
    public boolean isDone() {
        return isDoneBonusGame() || states.hasNotBonus();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.empty();
    }

    private boolean isDoneBonusGame() {
        return states.size() == BONUS_STATE_SIZE;
    }

    @Override
    public Score score() {
        if (states.isEmpty() || !isDone()) {
            return Score.ofProgress();
        }
        return sumCurrentFrameScore();
    }

    @Override
    protected Score additionalCalculate(Score score) {
        if (isAdditionalScore(score)) {
            return accumulateBeforeScore(score);
        }
        return Score.ofProgress();
    }

    private boolean isAdditionalScore(Score score) {
        return states.toList().size() >= score.left();
    }
}
