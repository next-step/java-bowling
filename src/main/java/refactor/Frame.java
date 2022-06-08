package refactor;

import java.util.Objects;

public class Frame {
    private Scores scores;
    private int subtotal;

    public Frame(Scores scores, int subtotal) {
        this.scores = scores;
        this.subtotal = subtotal;
    }

    public Frame() {
        this(new Scores(), 0);
    }

    public Frame(int subtotal) {
        this(new Scores(), subtotal);
    }


    public Frame pitches() {
        return pitch(this.scores);
    }

    private Frame pitch(Scores scores) {
        if (!scores.done()) {
            return pitch(scores.pitchRandom());
        }
        return new Frame(scores, subtotal + scores.sum());
    }

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

    public Frame accumulatedNextFrame(Frame next) {
        return new Frame(this.subtotal + next.subtotal);
    }
}
