package refactor;

import java.util.Objects;

import static bowling.util.Const.RANDOM;

public class Frame {
    private static int MAX_SCORE = 10;
    private final int score;
    private final int remaining;

    public Frame(int score, int remaining) {
        validate(score);
        this.score = score;
        this.remaining = remaining;
    }

    private void validate(int score) {
        if (score > 10) {
            throw new IllegalArgumentException("score cannot be over 10.");
        }
    }

    public Frame() {
        this(0, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return score == frame.score && remaining ==frame.remaining;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remaining);
    }


    public Frame pitchManual(int numberOfPins) {
        return new Frame(this.score + numberOfPins, remaining - 1);
    }

    public Frame pitch() {
        int numberOfPins = RANDOM.nextInt(MAX_SCORE - this.score + 1);
        return new Frame(numberOfPins, this.remaining - 1);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "score=" + score +
                ", remaining=" + remaining +
                '}';
    }

    public int score() {
        return this.score;
    }
}
