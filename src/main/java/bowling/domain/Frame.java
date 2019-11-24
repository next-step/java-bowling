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
        return new Frame(position, null);
    }

    public static Frame first(int position, int point) {
        return new Frame(position, Score.first(point));
    }

    public Frame second(int point) {
        return new Frame(this.position, this.score.second(point));
    }

    public int getFirstScore() {
        return this.score.getFirstPoint();
    }

    public int getSecondScore() {
        return this.score.getSecondPoint();
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
