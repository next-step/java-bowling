package bowling.frame.domain;

import bowling.pin.domain.Pin;
import bowling.state.domain.Ready;
import bowling.state.domain.State;
import java.util.Objects;

public class NormalFrame implements Frame {

    private final Index idx;
    private State state;
    private Frame next;

    public NormalFrame(Index idx) {
        this.idx = idx;
        this.state = Ready.of();
    }

    public static Frame of(Index idx) {
        return new NormalFrame(idx);
    }

    @Override
    public Frame roll(Pin felled) {
        this.state = state.roll(felled);
        return state.isEnd() ? generate() : this;
    }

    private Frame generate() {
        if (idx.isThreshold()) {
            this.next = Frame.end();
            return this.next;
        }
        this.next = NormalFrame.of(idx.increment());
        return this.next;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Frame next() {
        return this.next;
    }

    @Override
    public Index getIndex() {
        return idx;
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        return calculate(score);
    }

    @Override
    public Score calculateScore(Score base) {
        Score score = state.calculate(base);
        return calculate(score);
    }

    private Score calculate(Score score) {
        if (score.isPending()) {
            return next == null ? score : next.calculateScore(score);
        }
        return score;
    }

    protected State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NormalFrame)) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(idx, that.idx) &&
            Objects.equals(getState(), that.getState()) &&
            Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, getState(), next);
    }

}
