package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.States;
import bowling.exception.NoActionBowlException;

public final class FinalFrame implements Frame {

    private final States states;
    private FinalRound finalRound;

    public FinalFrame() {
        this.states = States.initialize();
        this.finalRound = FinalRound.initialize();
    }

    @Override
    public Frame bowl(final int pins) {
        return bowl(Pins.valueOf(pins));
    }

    @Override
    public final Frame bowl(final Pins fallPins) {
        validateRound();
        finalRound = finalRound.next();
        final State current = states.current();
        if (current.isFinish()) {
            states.add(generateReady().bowl(fallPins));
            return this;
        }
        states.remove();
        states.add(current.bowl(fallPins));
        return this;
    }

    private final State generateReady() {
        return State.initialize();
    }

    private final void validateRound() {
        if (isFinish()) {
            throw new NoActionBowlException();
        }
    }

    @Override
    public final boolean isFinish() {
        return finalRound.isFinish(hasBonusRound());
    }

    @Override
    public final Score score() {
        return states.score();
    }

    @Override
    public final int sequence() {
        return Frame.LAST_SEQUENCE;
    }

    @Override
    public final Score calculateAdditionalScore(final Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        return states.calculateAdditionalScore(beforeScore);
    }

    @Override
    public final String description() {
        return states.description();
    }

    private final boolean hasBonusRound() {
        return states.hasBonusRound();
    }

}
