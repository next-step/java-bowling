package refactor;


import java.util.List;
import java.util.Objects;

public class Frame {
    private Scores scores;
    private Subtotal subtotal;

    public Frame(Scores scores, Subtotal subtotal) {
        this.scores = scores;
        this.subtotal = subtotal;
    }

    public Frame() {
        this(new Scores(), new Subtotal());
    }

//    public Frame(int subtotal) {
//        this(new Scores(), subtotal);
//    }

//    public Frame pitches() {
//        return pitch(this.scores);
//    }

//    private Frame pitch(Scores scores) {
//        if (!scores.done()) {
//            return pitch(scores.pitchRandom());
//        }
//        return new Frame(scores, subtotal + scores.sum());
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return subtotal == frame.subtotal && Objects.equals(scores, frame.scores);
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

//    public Frame accumulatedNextFrame(Frame next) {
//        return new Frame(this.subtotal + next.subtotal);
//    }

    public void pitchManual(int numPins, Frames frames) {
        Scores scores = this.scores.pitch(numPins);
        pitch(scores, frames);
    }

    public void pitchRandom(Frames frames) {
        Scores scores = this.scores.pitchRandom();
        pitch(scores, frames);
    }

    private void pitch(Scores scores, Frames frames) {
        if (scores.done()) {
            this.scores = scores;
            State state = evaluateState(scores);
            this.subtotal = new Subtotal(state, this.subtotal.add(scores.sum()));
        }
        this.scores = scores;
        if (frames.index(this) > 0 && frames.prev(this).subtotal.state() != State.DONE) {
            frames.prev(this).subtotal.accumulateBonus(this.scores.lastScore());
        }
    }

    private State evaluateState(Scores scores) {
        if (scores.isStrike()) {
            return State.WAIT_TWICE;
        }
        if (scores.sum() == 10) {
            return  State.WAIT_ONCE;
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

    public void updateSubtotal(Subtotal subtotal) {
        this.subtotal = new Subtotal(State.WAITING, subtotal.value());
    }
}
