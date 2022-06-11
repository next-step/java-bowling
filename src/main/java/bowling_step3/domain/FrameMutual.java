package bowling_step3.domain;

import java.util.List;
import java.util.Objects;

public abstract class FrameMutual {
    Scores scores;
    Subtotal subtotal;

    public void pitchManual(int numPins, Frames frames) {
        Scores scores = this.scores.pitch(numPins);
        pitch(scores, frames);
    }

    public void pitchRandom(Frames frames) {
        Scores scores = this.scores.pitchRandom();
        pitch(scores, frames);
    }

    abstract void pitch(Scores scores, Frames frames);

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

    abstract void updateNextSubtotal(Subtotal subtotal);

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
