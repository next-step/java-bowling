package bowling.domain;


import java.util.List;
import java.util.Objects;

public class FrameGeneral implements Frame {
    private Scores scores;
    private Subtotal subtotal;

    public FrameGeneral(Scores scores, Subtotal subtotal) {
        this.scores = scores;
        this.subtotal = subtotal;
    }

    public FrameGeneral() {
        this(new Scores(), new Subtotal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameGeneral that = (FrameGeneral) o;
        return Objects.equals(scores, that.scores) && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, subtotal);
    }

    @Override
    public String
    toString() {
        return "Frame{" +
                "scores=" + scores +
                ", subtotal=" + subtotal +
                '}';
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
