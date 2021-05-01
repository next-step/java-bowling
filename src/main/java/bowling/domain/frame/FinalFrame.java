package bowling.domain.frame;

import java.util.Optional;

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
        return isDoneBonusGame() || isDoneNormalFrames();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.empty();
    }

    private boolean isDoneBonusGame() {
        return states.size() == BONUS_STATE_SIZE;
    }

    private boolean isDoneNormalFrames() {
        return states.size() == NORMAL_STATE_SIZE
            && states.hasNotBonus();
    }
}
