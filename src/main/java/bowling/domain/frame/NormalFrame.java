package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.running.Ready;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final FrameIndex index;
    private ThrowingState state;

    private NormalFrame(FrameIndex index) {
        this.index = index;
        this.state = Ready.create();
    }

    static Frame first() {
        return new NormalFrame(FrameIndex.first());
    }

    @Override
    public Frame bowl(Pins pins) {
        this.state = state.bowl(pins);
        if (isLastBeforeFrame()) {
            return LastFrame.first();
        }
        if (state.isEnd()) {
            return new NormalFrame(index.next());
        }
        return this;
    }

    @Override
    public int getIndex() {
        return index.getIndex();
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public String symbol() {
        return state.symbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(getIndex(), that.getIndex()) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), state);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", state=" + state +
                '}';
    }

    private boolean isLastBeforeFrame() {
        return state.isEnd() && index.lastBeforeIndex();
    }
}
