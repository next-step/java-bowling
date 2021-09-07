package step2.domain;

import java.util.Objects;

public class Frame {
    private static final int INITIAL_NUM_OF_BOWLING_PINS = 10;
    private static final int INITIAL_SCORE = 0;

    private int pins;
    private int score;

    public Frame() {
        this(INITIAL_NUM_OF_BOWLING_PINS, INITIAL_SCORE);
    }

    public Frame(int pins, int score) {
        this.pins = pins;
        this.score = score;
    }

    public void knockDown(int numOfPin) {
        pins -= numOfPin;
        score += numOfPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return pins == frame.pins && score == frame.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, score);
    }
}
