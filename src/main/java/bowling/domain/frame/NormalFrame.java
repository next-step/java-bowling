package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final Index idx;
    private State state;

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
            return Frame.endFrame();
        }
        return NormalFrame.of(idx.increment());
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public Score calculateScore(Score baseScore, Frames frames) {
        Score score = state.calculate(baseScore);
        return calculate(score, frames);
    }

    @Override
    public Score getScore(Frames frames) {
        Score score = state.getScore();
        return calculate(score, frames);
    }

    private Score calculate(Score baseScore, Frames frames) {
        if (baseScore.isPending()) {
            Frame nextFrame = frames.getNextFrame(idx);
            return nextFrame == null ? baseScore : nextFrame.calculateScore(baseScore, frames);
        }
        return baseScore;
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
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, state);
    }
}
