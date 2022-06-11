package bowling_step3.domain;

public class FrameGeneral extends FrameMutual implements Frame {
    public FrameGeneral() {
        super();
    }

    public FrameGeneral(Scores scores, Subtotal subtotal) {
        super(scores, subtotal);
    }

    protected void pitch(Scores scores, Frames frames) {
        this.scores = scores;
        if (scores.done()) {
            State state = evaluateState(scores);
            this.subtotal = new Subtotal(state, this.subtotal.value() + scores.sum());
        }
        if (frames.index(this) > 0 && frames.prev(this).subtotal().state().waiting()) {
            frames.prev(this).subtotal().accumulateBonus(this.scores.lastScore());
            this.subtotal = new Subtotal(this.subtotal.state(), this.subtotal.value() + this.scores.lastScore());
        }
    }

    protected State evaluateState(Scores scores) {
        if (scores.isStrike()) {
            return State.WAIT_TWICE;
        }
        if (scores.sum() == 10) {
            return State.WAIT_ONCE;
        }
        return State.DONE;
    }

    public void updateNextSubtotal(Subtotal subtotal) {
        this.subtotal = new Subtotal(State.INIT, subtotal.value());
    }
}
