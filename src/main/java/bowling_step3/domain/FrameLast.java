package bowling_step3.domain;

public class FrameLast extends FrameMutual implements Frame {
    public FrameLast() {
        super();
    }

    public FrameLast(Scores scores, Subtotal subtotal) {
        super(scores, subtotal);
    }

    protected Scores evaluateScore(Scores scores) {
        return scores.evaluateLastBonus();
    }

    protected State evaluateState(Scores scores) {
        if (scores.done()) {
            return State.DONE;
        }
        return State.INIT;
    }

    @Override
    public Subtotal calculateAdditionalScore(Subtotal subtotal) {
        if (subtotal.state() == State.WAIT_TWICE) {
            return new Subtotal(State.DONE, subtotal.value() + getAdditionalForStrike());
        }
        if (subtotal.state() == State.WAIT_ONCE) {
            return new Subtotal(State.DONE, subtotal.value() + getFirstScore());
        }
        throw new UnsupportedOperationException("Cannot calculate additional yet.");
    }
}
