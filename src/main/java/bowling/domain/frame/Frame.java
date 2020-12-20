package bowling.domain.frame;

import bowling.domain.score.Pitch;
import bowling.domain.score.Score;
import bowling.state.BowlingState;

import java.util.Objects;

/**
 * Created By mand2 on 2020-12-18.
 */
public abstract class Frame {

    protected final int index;
    protected Score score;

    protected Frame(int index) {
        this(index, new Score());
    }

    protected Frame(int index, Score score) {
        this.index = index;
        this.score = score;
    }


    public abstract void pitch(Pitch score);

    public abstract boolean isPlayable();

    public abstract boolean isEnd();

    public abstract void checkState();

    public abstract BowlingState getState();

    public int getIndex() {
        return index;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return getIndex() == frame.getIndex() ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex());
    }
}
