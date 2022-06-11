package bowling_step3.domain;

import java.util.ArrayList;

public class FrameLast extends FrameMutual implements Frame {

    public FrameLast() {
        this(new Scores(new ArrayList<>(), 2), new Subtotal());
    }

    public FrameLast(Scores scores, Subtotal subtotal) {
        this.scores = scores;
        this.subtotal = subtotal;
    }

    protected void pitch(Scores scores, Frames frames) {
        this.scores = scores.evaluateLastBonus();
        if (this.scores.done()) {
            State state = evaluateState(this.scores);
            this.subtotal = new Subtotal(state, this.subtotal.value() + this.scores.sum());
        }
        if (frames.index(this) > 0 && frames.prev(this).subtotal().state().waiting()) {
            frames.prev(this).subtotal().accumulateBonus(this.scores.lastScore());
            this.subtotal = new Subtotal(this.subtotal.state(), this.subtotal.value() + this.scores.lastScore());
        }
    }

    protected State evaluateState(Scores scores) {
        if (scores.done()) {
            return State.DONE;
        }
        return State.INIT;
    }

    public void updateNextSubtotal(Subtotal subtotal) {
       throw new UnsupportedOperationException("There is no next Frame.");
    }
}
