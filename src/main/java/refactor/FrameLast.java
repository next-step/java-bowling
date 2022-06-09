package refactor;

import java.util.ArrayList;
import java.util.List;

public class FrameLast implements Frame {
    private Scores scores;
    private Subtotal subtotal;

    public FrameLast() {
        this.scores = new Scores(new ArrayList<>(), 2);
        this.subtotal = new Subtotal();
    }

    public void pitchManual(int numPins, Frames frames) {
        Scores scores = this.scores.pitch(numPins);
        pitch(scores, frames);
    }

    public void pitchRandom(Frames frames) {
        Scores scores = this.scores.pitchRandom();
        pitch(scores, frames);
    }

    private void pitch(Scores scores, Frames frames) {
        this.scores = scores.evaluateLastBonus();
        if (this.scores.done()) {
            State state = evaluateState(this.scores);
            this.subtotal = new Subtotal(state, this.subtotal.value() + this.scores.sum());
        }
        if (frames.index(this) > 0 && frames.prev(this).subtotal().state().waiting()) {
            frames.prev(this).subtotal().accumulateBonus(this.scores.lastScore());
//            this.updateSubtotal(new Subtotal(this.subtotal.state(), frames.prev(this).subtotal.value()));
            this.subtotal = new Subtotal(this.subtotal.state(), this.subtotal.value() + this.scores.lastScore());
        }
    }

    private State evaluateState(Scores scores) {
        if (scores.isStrike()) {
            return State.WAIT_TWICE;
        }
        if (scores.sum() == 10) {
            return State.WAIT_ONCE;
        }
        return State.DONE;
    }


    public Subtotal subtotal() {
        return this.subtotal;
    }

    public boolean done() {
        return this.scores.done();
    }

    public List<Integer> scores() {
        return this.scores.scores();
    }

    public void updateNextSubtotal(Subtotal subtotal) {
        this.subtotal = new Subtotal(State.INIT, subtotal.value());
    }
}
