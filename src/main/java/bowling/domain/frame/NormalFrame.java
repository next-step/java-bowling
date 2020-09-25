package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final Index idx;
    private State state;
    private Frame nextFrame;

    private NormalFrame(Index idx) {
        this.idx = idx;
        this.state = Ready.of();
    }

    public static Frame of(Index idx) {
        return new NormalFrame(idx);
    }

    @Override
    public Frame bowl(Pin felledPin) {
        this.state = state.bowl(felledPin);
        return state.isEnd() ? generate() : this;
    }

    private Frame generate() {
        if (idx.isLast()) {
            this.nextFrame = Frame.endFrame();
            return this.nextFrame;
        }
        this.nextFrame = NormalFrame.of(idx.increment());
        return this.nextFrame;
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public Score calculateScore(Score baseScore) {
        Score score = state.calculate(baseScore);
        return calculate(score);
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        return calculate(score);
    }

    private Score calculate(Score baseScore) {
        if (baseScore.isPending()) {
            return nextFrame == null ? baseScore : nextFrame.calculateScore(baseScore);
        }
        return baseScore;
    }

    @Override
    public boolean hasNext() {
        return nextFrame != null;
    }

    @Override
    public Frame next() {
        return this.nextFrame;
    }

    @Override
    public Index getIndex() {
        return this.idx;
    }

    public State getState() {
        return this.state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(idx, that.idx) &&
                Objects.equals(state, that.state) &&
                Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, state, nextFrame);
    }
}
