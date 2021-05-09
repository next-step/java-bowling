package bowling.domain.frame;

import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.States;
import bowling.domain.state.running.Ready;
import bowling.exception.NoActionBowlException;

public final class FinalFrame extends Frame {

    private final States states;
    private FinalRound finalRound;

    private FinalFrame() {
        this.states = States.initialize();
        this.finalRound = FinalRound.initialize();
    }

    public static final Frame initialize() {
        return new FinalFrame();
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
        return Ready.initialize();
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

    private final boolean hasBonusRound() {
        return states.hasBonusRound();
    }

}
