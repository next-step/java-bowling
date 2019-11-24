package bowling.domain;

import java.util.Objects;

public class Frame {

    private int position;
    private int score;

    public Frame(int position, int score) {
        this.position = position;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return position == frame.position &&
                score == frame.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, score);
    }
}
