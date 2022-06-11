package bowling_step3.domain;

import java.util.List;
import java.util.Objects;

public abstract class FrameMutual implements Frame {
    Scores scores;
    Subtotal subtotal;

    public FrameMutual(Scores scores, Subtotal subtotal) {
        this.scores = scores;
        this.subtotal = subtotal;
    }

    public FrameMutual() {
        this(new Scores(), new Subtotal());
    }

    public void playManual(int numPins, Frames frames) {
        Scores scores = this.scores.pitch(numPins);
        updateScore(scores, frames);
        updateSubtotal(frames);
    }

    public void playRandom(Frames frames) {
        Scores scores = this.scores.pitchRandom();
        updateScore(scores, frames);
        updateSubtotal(frames);
    }

    abstract void updateScore(Scores scores, Frames frames);

    public void updateSubtotal(Frames frames) {
        if (this.scores.done()) {
            State state = evaluateState(this.scores);
            this.subtotal = new Subtotal(state, this.subtotal.value() + this.scores.sum());
        }
        if (frames.index(this) > 0 && frames.prev(this).subtotal().state().waiting()) {
            frames.prev(this).subtotal().accumulateBonus(this.scores.lastScore());
            this.subtotal = new Subtotal(this.subtotal.state(), this.subtotal.value() + this.scores.lastScore());
        }
    }

    abstract State evaluateState(Scores scores);


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

    @Override
    public String toString() {
        return "FrameMutual{" +
                "scores=" + scores +
                ", subtotal=" + subtotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameMutual that = (FrameMutual) o;
        return Objects.equals(scores, that.scores) && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, subtotal);
    }
}
