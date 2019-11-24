package bowling.domain;

import java.util.Objects;

public class Frame {

    private int position;
    private Score score;

    private Frame(int position, Score score) {
        this.position = position;
        this.score = score;
    }

    public static Frame ready(int position) {
        return of(position, null);
    }

    public Frame first(int point) {
        return of(position, Score.first(point));
    }

    public Frame second(int point) {
        return of(position, score.second(point));
    }

    public static Frame of(int position, Score score) {
        return new Frame(position, score);
    }

    public int getPoint() {
        return score.getPoint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return position == frame.position &&
                Objects.equals(score, frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, score);
    }
}
