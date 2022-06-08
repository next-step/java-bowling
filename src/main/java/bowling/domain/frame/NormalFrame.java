package bowling.domain.frame;

import bowling.domain.Content;
import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import bowling.exception.NotCreateFrameException;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final Content content;
    private State state;

    private NormalFrame(Content content) {
        this.state = StateFactory.initialState();
        this.content = content;
    }

    public static NormalFrame initialize() {
        return new NormalFrame(Content.initialize());
    }

    @Override
    public Frame next() throws NotCreateFrameException {
        if (content.isNextFrameNoLast()) {
            return last();
        }
        return new NormalFrame(content.next(new Score()));
    }

    private Frame last() {
        return new FinalFrame(content.next(new Score()));
    }

    @Override
    public void bowling(int hit) {
        state = state.bowl(hit);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public boolean hasLastBonusChance() {
        return false;
    }

    @Override
    public Content content() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(state, that.state) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, content);
    }

    @Override
    public String toString() {
        return state.description();
    }
}
