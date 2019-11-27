package bowling.domain;

import java.util.Objects;

public class Frame {

    private Frame next;
    private Score score;
    private int order;

    public Frame(int order) {
        this.order = order;
        this.score = (order == 10) ? new FinalScore() : new EmptyScore();
    }

    public Frame ofNext() {
        Frame newFrame = new Frame(order + 1);
        next = newFrame;
        return newFrame;
    }

    public boolean isFinished() {
        return score.isFinished();
    }

    public void bowl(int pin) {
        score = score.ofNext(pin);
    }

    public Score getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return order == frame.order &&
                Objects.equals(next, frame.next) &&
                Objects.equals(score, frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, score, order);
    }
}
