package refactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FrameLast implements Frame {
    private Scores scores;
    private Subtotal subtotal;

    public FrameLast() {
        this(new Scores(new ArrayList<>(), 2), new Subtotal());
    }

    public FrameLast(Scores scores, Subtotal subtotal) {
        this.scores = scores ;
        this.subtotal = subtotal;
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
            this.subtotal = new Subtotal(this.subtotal.state(), this.subtotal.value() + this.scores.lastScore());
        }
    }

    private State evaluateState(Scores scores) {
        if (scores.done()) {
            return State.DONE;
        }
        return State.INIT;
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

    @Override
    public String toString() {
        return "FrameLast{" +
                "scores=" + scores +
                ", subtotal=" + subtotal +
                '}';
    }

    public void updateNextSubtotal(Subtotal subtotal) {
        this.subtotal = new Subtotal(State.INIT, subtotal.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameLast frameLast = (FrameLast) o;
        return Objects.equals(scores, frameLast.scores) && Objects.equals(subtotal, frameLast.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, subtotal);
    }
}
